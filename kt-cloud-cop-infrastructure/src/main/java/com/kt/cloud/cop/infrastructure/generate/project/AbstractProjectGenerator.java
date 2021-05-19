package com.kt.cloud.cop.infrastructure.generate.project;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.kt.cloud.cop.infrastructure.generate.GenerateException;
import com.kt.cloud.cop.infrastructure.generate.engine.TemplateEngine;
import com.kt.component.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Component
public  abstract class AbstractProjectGenerator implements ProjectGenerator {

    private String temp = "/Users/chenjiawei/code/myself/kt-cloud-cop";

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public File generator(Map<String, Object> extProperties) {

        File rootProject = copyToTemp(getScaffold());

        check(rootProject);

        doProcess(rootProject, extProperties);

        postProcess(rootProject, extProperties);

        return rootProject;
    }

    protected void postProcess(File rootProject, Map<String, Object> extProperties) {

    }

    private void check(File rootProject) {
        if (!rootProject.isDirectory()) {
            throw new GenerateException("骨架工程路径不合法");
        }
    }

    protected void doProcess(File file, Map<String, Object> params) {
        File[] subFiles = file.listFiles();
        if (ArrayUtil.isEmpty(subFiles)) {
            return;
        }
        
        for (File fileItem : subFiles) {
            if (fileItem.isFile() && !getIgnoreFileSuffixList().contains(FileUtil.getSuffix(fileItem))) {
                processFile(fileItem, params);
            } else if (fileItem.isDirectory() && !getIgnoreFileDirectories().contains(fileItem.getName())) {
                File renameFile = attemptRenameDir(fileItem, params);
                processDirectory(renameFile, params);
            }
        }
    }

    protected void processDirectory(File fileItem, Map<String, Object> params) {
        doProcess(fileItem, params);
    }

    protected abstract List<String> getIgnoreFileDirectories();

    abstract protected List<String> getIgnoreFileSuffixList();

    protected abstract String getScaffoldName();

    private File getScaffold() {
        ClassPathResource classPathResource = new ClassPathResource("/scaffold/" + getScaffoldName());
        return classPathResource.getFile();
    }

    /**
     * 1.创建一个临时目录
     * 2.把脚手架内容复制到临时目录
     */
    private File copyToTemp(File scaffoldFile) {
        File tempDir = new File(temp + File.separator + IdUtil.fastSimpleUUID());
        if (tempDir.exists()) {
            FileUtil.del(tempDir);
        }
        boolean mkSuccess = tempDir.mkdir();
        if (!mkSuccess) {
            throw new BizException("临时目录创建失败");
        }
        FileUtil.copyContent(scaffoldFile, tempDir, true);
        return tempDir;
    }

    protected void processFile(File subFile, Map<String, Object> params) {
        String template = FileUtil.readUtf8String(subFile);
        try {
            templateEngine.renderTo(params, template, new FileOutputStream(subFile));
        } catch (FileNotFoundException e) {
            //
        }
//        FileUtil.writeUtf8String(doRender(params, template), subFile);
    }

    protected File attemptRenameDir(File fileItem, Map<String, Object> projectGenerateParam) {
        String originalName = fileItem.getName();
        String render = doRender(projectGenerateParam, originalName);
        if (!render.equals(originalName)) {
            return FileUtil.rename(fileItem, render, true);
        }
        return fileItem;
    }

    private String doRender(Map<String, Object> params, String template) {
        return templateEngine.render(params, template);
    }
}
