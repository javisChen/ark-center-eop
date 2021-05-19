package com.kt.cloud.cop.module.codeproject.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.kt.cloud.cop.infrastructure.generate.model.CodeGenerateModel;
import com.kt.cloud.cop.infrastructure.generate.model.JavaProjectGenerateParam;
import com.kt.cloud.cop.module.codeproject.BaseTests;
import com.kt.cloud.cop.module.codeproject.GenCodeProjectDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void genCodeProject() {
        GenCodeProjectDTO genCodeProjectDTO = new GenCodeProjectDTO();
        String artifactId = "test-demo";
        String groupId = "com.kt.cloud";
        String packageName = "com.kt.cloud";
        genCodeProjectDTO.setName(artifactId);
        genCodeProjectDTO.setType("java");
        genCodeProjectDTO.setDescription("demo1237");
        genCodeProjectDTO.setDeleteTempFileAfterGen(false);

        JavaProjectGenerateParam javaProjectGenerateParam = new JavaProjectGenerateParam();
        javaProjectGenerateParam.setArtifactId(artifactId);
        javaProjectGenerateParam.setGroupId(groupId);
        javaProjectGenerateParam.setPackageName(packageName);
        javaProjectGenerateParam.setDsUrl("jdbc:mysql://localhost:3306/cop?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True");
        javaProjectGenerateParam.setDsUsername("root");
        javaProjectGenerateParam.setDsPassword("Root1234!@#$");
        javaProjectGenerateParam.setGenDaoCode(true);

        genCodeProjectDTO.setExtProperties(JSON.toJSONString(BeanUtil.beanToMap(javaProjectGenerateParam)));

        iCodeProjectService.genCodeProject(genCodeProjectDTO);
    }

    @Test
    public void genCodeProjectTemp() {
        File original = new File("/Users/chenjiawei/code/myself/kt-cloud-cop/kt-cloud-cop-service/src/main/resources/scaffold/java/{{artifactId}}/{{artifactId}}-dao");
        FileUtil.rename(original, "test", true);
    }
}