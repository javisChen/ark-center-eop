package com.kt.cloud.cop.infrastructure.generate.project;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.kt.cloud.cop.infrastructure.generate.code.CodeGenerator;
import com.kt.cloud.cop.infrastructure.generate.model.CodeGenerateModel;
import com.kt.cloud.cop.infrastructure.generate.model.JavaProjectGenerateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component("SpringCloudProjectGenerator")
public class SpringCloudProjectGenerator extends AbstractProjectGenerator {

    @Autowired
    private CodeGenerator codeGenerator;

    @Override
    protected void postProcess(File rootProject, Map<String, Object> extProperties) {
        JavaProjectGenerateParam param = convertToParam(extProperties);
        if (param.getGenDaoCode()) {
            genDaoCode(rootProject, param);
        }

    }

    private void genDaoCode(File rootProject, JavaProjectGenerateParam param) {
        String daoModulePath = findDaoModule(rootProject).getAbsolutePath() + "/src/main/java";
        CodeGenerateModel model = getCodeGenerateModel(param, daoModulePath);
        codeGenerator.execute(model);
    }

    public File findDaoModule(File rootProject) {
        if (rootProject == null || ArrayUtil.isEmpty(rootProject.listFiles())) {
            return null;
        }
        return Stream
                .of(rootProject.listFiles()[0].listFiles())
                .filter(file -> file.getName().endsWith("-dao")).findFirst().orElse(null);
    }

    private JavaProjectGenerateParam convertToParam(Map<String, Object> extProperties) {
        return BeanUtil.mapToBean(extProperties, JavaProjectGenerateParam.class, true, CopyOptions.create());
    }

    private CodeGenerateModel getCodeGenerateModel(JavaProjectGenerateParam extProperties, String absolutePath) {
        CodeGenerateModel codeGenerateModel = new CodeGenerateModel();
        codeGenerateModel.setUrl(extProperties.getDsUrl());
        codeGenerateModel.setUsername(extProperties.getDsUsername());
        codeGenerateModel.setPassword(extProperties.getDsPassword());
        codeGenerateModel.setOutputDir(absolutePath);
        codeGenerateModel.setGenDaoCode(extProperties.getGenDaoCode());
        codeGenerateModel.setParent(extProperties.getPackageName() + ".dao");
        codeGenerateModel.setInclude(extProperties.getInclude());
        return codeGenerateModel;
    }

    @Override
    protected void processDirectory(File fileItem, Map<String, Object> params) {

        String packageName = MapUtil.getStr(params, "packageName");

        if (isJavaDirectory(fileItem)) {

            // 把Java目录下的文件读取出来
            File[] originFiles = fileItem.listFiles();

            // 创建新目录
            File newDir = createDirByPackageName(packageName, fileItem);

            // 把原目录的文件move到新目录下
            moveOriginFilesToNewDir(originFiles, newDir);

            // 继续加工目录文件
            doProcess(newDir, params);

        } else {
            super.processDirectory(fileItem, params);
        }
    }

    private boolean isJavaDirectory(File fileItem) {
        return fileItem.getName().equals("java");
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
        String replace = packageNameConvertToPath(packageName);
        File file = new File(subFile.getAbsolutePath() + File.separator + replace);
        boolean mkdirs = file.mkdirs();
        return file;
    }

    private String packageNameConvertToPath(String packageName) {
        return StrUtil.replace(packageName, ".", "/");
    }


    @Override
    protected List<String> getIgnoreFileDirectories() {
        return CollectionUtil.newArrayList(".git");
    }

    @Override
    protected List<String> getIgnoreFileSuffixList() {
        return CollectionUtil.newArrayList("iml", "idea");
    }

    @Override
    protected String getScaffoldName() {
        return "java";
    }
}
