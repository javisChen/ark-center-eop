package com.kt.cloud.codeproject.generate;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.kt.cloud.codeproject.generate.model.JavaProjectGenerateParam;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JavaProjectGenerator implements ProjectGenerator<JavaProjectGenerateParam> {


    private String temp = "/Users/chenjiawei/code/myself/kt-cloud-code-project";
    private List<String> filterSuffix = CollectionUtil.newArrayList("iml", "idea");
    private List<String> filterDict = CollectionUtil.newArrayList(".git");

    @Override
    public boolean generator(JavaProjectGenerateParam projectGenerateParam) {
        // 获取脚手架
        File scaffold = getScaffold();

        // 复制到临时目录
        File rootProject = copyToTemp(scaffold, projectGenerateParam);

        if (!rootProject.isDirectory()) {
            throw new GenerateException("骨架工程路径不合法");
        }

        doProcess(rootProject, projectGenerateParam);

        return true;
    }

    private File getScaffold() {
        String scaffold = "/Users/chenjiawei/code/myself/kt-cloud-code-project/kt-cloud-code-project-service/src/main/resources/scaffold/java";
        return new File(scaffold);
    }

    private GroupTemplate getGroupTemplate() {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            cfg.setPlaceholderStart("{{");
            cfg.setPlaceholderEnd("}}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GroupTemplate(resourceLoader, cfg);
    }

    private File copyToTemp(File scaffoldFile, JavaProjectGenerateParam projectGenerateParam) {
        String tempId = IdUtil.fastSimpleUUID();
        // 把脚手架复制到一个临时目录，在临时目录里面
        File tempDir = new File(temp + File.separator + tempId);
        if (tempDir.exists()) {
            FileUtil.del(tempDir);
        }
        tempDir.mkdir();

        FileUtil.copyContent(scaffoldFile, tempDir, true);

        return tempDir;
    }

    private void doProcess(File subFile, JavaProjectGenerateParam projectGenerateParam) {
        File[] subFiles = subFile.listFiles();
        if (ArrayUtil.isEmpty(subFiles)) {
            return;
        }

        for (File fileItem : subFiles) {
            if (fileItem.isFile() && !filterSuffix.contains(FileUtil.getSuffix(fileItem))) {
                processFile(fileItem, projectGenerateParam);
            } else if (fileItem.isDirectory()) {
                if (fileItem.getName().equals("java")) {

                    // 把Java目录下的文件读取出来
                    File[] originFiles = fileItem.listFiles();

                    // 创建新目录
                    File newDir = createDirByPackageName(projectGenerateParam.getPackageName(), fileItem);

                    // 把原目录的文件move到新目录下
                    moveOriginFilesToNewDir(originFiles, newDir);

                    // 继续加工目录文件
                    doProcess(newDir, projectGenerateParam);

                } else if (!filterDict.contains(fileItem.getName())) {
                    File renameFile = attemptRenameDir(fileItem, projectGenerateParam);
                    doProcess(renameFile, projectGenerateParam);
                }
            }
        }
    }

    private File attemptRenameDir(File fileItem, JavaProjectGenerateParam projectGenerateParam) {
        String originalName = fileItem.getName();
        Template template = doRender(projectGenerateParam, originalName);
        String render = template.render();
        if (!render.equals(originalName)) {
            return FileUtil.rename(fileItem, render, true);
        }
        return fileItem;
    }

    private Template doRender(JavaProjectGenerateParam projectGenerateParam, String originalName) {
        Template template = getGroupTemplate().getTemplate(originalName);
        template.binding(BeanUtil.beanToMap(projectGenerateParam));
        return template;
    }

    private void moveOriginFilesToNewDir(File[] originFiles, File file) {
        if (ArrayUtil.isNotEmpty(originFiles)) {
            for (File listFile : originFiles) {
                FileUtil.move(listFile, file, true);
            }
        }
    }

    /**
     * 根据package创建新目录
     */
    private File createDirByPackageName(String packageName, File subFile) {
        String replace = StrUtil.replace(packageName, ".", "/");
        File file = new File(subFile.getAbsolutePath() + File.separator + replace);
        file.mkdirs();
        return file;
    }

    private void processFile(File subFile, JavaProjectGenerateParam projectGenerateParam) {
        String s = FileUtil.readUtf8String(subFile);
        Template t = doRender(projectGenerateParam, s);
        FileUtil.writeUtf8String(t.render(), subFile);
    }

}
