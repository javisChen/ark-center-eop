package com.kt.cloud.cop.module.codeproject.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.cop.BaseTests;
import com.kt.cloud.cop.client.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.cop.dao.entity.ProjectBasic;
import com.kt.cloud.cop.infrastructure.generate.model.JavaProjectGenerateParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class CodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateCodeProject() {
        CodeProjectCreateCmd cmd = new CodeProjectCreateCmd();
        String artifactId = "success-demo-123";
        String groupId = "com.kt.cloud";
        String packageName = "com.kt.cloud";
        cmd.setCode(artifactId);
        cmd.setType(ProjectBasic.Type.BACKEND.getValue());
        cmd.setScaffold(ProjectBasic.Scaffold.SpringCloud.getValue());
        cmd.setDescription("demo1237");
        cmd.setDeleteTempFileAfterGen(false);
        cmd.setCreateGitRepos(true);

        JavaProjectGenerateParam javaProjectGenerateParam = new JavaProjectGenerateParam();
        javaProjectGenerateParam.setArtifactId(artifactId);
        javaProjectGenerateParam.setGroupId(groupId);
        javaProjectGenerateParam.setPackageName(packageName);
        javaProjectGenerateParam.setDsUrl("jdbc:mysql://localhost:3306/cop?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True");
        javaProjectGenerateParam.setDsUsername("root");
        javaProjectGenerateParam.setDsPassword("Root1234!@#$");
        javaProjectGenerateParam.setGenDaoCode(true);

        String extProperties = JSON.toJSONString(BeanUtil.beanToMap(javaProjectGenerateParam));
        cmd.setExtProperties(extProperties);
        System.out.println(JSONObject.toJSONString(cmd));
        iCodeProjectService.createCodeProject(cmd);
    }

    @Test
    public void genCodeProjectTemp() {
        File original = new File("/Users/chenjiawei/code/myself/kt-cloud-cop/kt-cloud-cop-service/src/main/resources/scaffold/java/{{artifactId}}/{{artifactId}}-dao");
        FileUtil.rename(original, "test", true);
    }

    @Test
    public void testGetEnums() {

    }
}