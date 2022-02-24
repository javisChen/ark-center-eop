package com.kt.cloud.eop.module.codeproject.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.eop.BaseTests;
import com.kt.cloud.eop.api.codeproject.cmd.CodeProjectCreateCmd;
import com.kt.cloud.eop.api.codeproject.enums.GenerateModeEnums;
import com.kt.cloud.eop.api.codeproject.enums.ReposSourceEnums;
import com.kt.cloud.eop.dao.entity.ProjectBasic;
import com.kt.cloud.eop.module.codeproject.generate.model.SpringCloudProjectGenerateParam;
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
        String groupId = "com.kt.cloud";
        String artifactId = "kt-cloud-order12";
        String packageName = "com.kt.cloud.order11";
        String description = "支付服务";
        cmd.setName(description);
        cmd.setCode(artifactId);
        cmd.setReposSource(ReposSourceEnums.CREATE_NEW.getValue());
        cmd.setType(ProjectBasic.Type.BACKEND.getValue());
        cmd.setScaffold(ProjectBasic.Scaffold.SpringCloud.getValue());
        cmd.setDescription(description);
        cmd.setDeleteTempFileAfterGen(false);
        cmd.setGenerateMode(GenerateModeEnums.SCAFFOLD.getValue());
        SpringCloudProjectGenerateParam javaProjectGenerateParam = new SpringCloudProjectGenerateParam();
        javaProjectGenerateParam.setArtifactId(artifactId);
        javaProjectGenerateParam.setGroupId(groupId);
        javaProjectGenerateParam.setPackageName(packageName);
        javaProjectGenerateParam.setDsUrl("jdbc:mysql://localhost:3306/cop?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True");
        javaProjectGenerateParam.setDsUsername("root");
        javaProjectGenerateParam.setDsPassword("Root1234!@#$");
        javaProjectGenerateParam.setGenDAOCode(false);

        String extProperties = JSON.toJSONString(BeanUtil.beanToMap(javaProjectGenerateParam));
        cmd.setExtProperties(extProperties);
        System.out.println(JSONObject.toJSONString(cmd));
        iCodeProjectService.createCodeProject(cmd);
    }

    @Test
    public void genCodeProjectTemp() {
        File original = new File("/Users/chenjiawei/code/myself/kt-cloud-eop/kt-cloud-eop-service/src/main/resources/scaffold/java/{{artifactId}}/{{artifactId}}-dao");
        FileUtil.rename(original, "test", true);
    }

    @Test
    public void testGetEnums() {

    }
}