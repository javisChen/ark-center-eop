package com.ark.center.eop.module.codeproject.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.ark.center.eop.BaseTests;
import com.ark.center.eop.dao.entity.ProjectBasic;
import com.ark.center.eop.module.codeproject.dto.cmd.CodeProjectCreateCmd;
import com.ark.center.eop.module.codeproject.dto.enums.GenerateModeEnums;
import com.ark.center.eop.module.codeproject.dto.enums.ReposSourceEnums;
import com.ark.center.eop.module.codeproject.generate.model.SpringCloudProjectGenerateParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

@ActiveProfiles("dev")
public class CodeProjectServiceTest extends BaseTests {

    @Autowired
    private ICodeProjectService iCodeProjectService;
    private final String dsUrl = "jdbc:mysql://gz-cynosdbmysql-grp-irl7x9ar.sql.tencentcdb.com:20716/commodity?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True";
    private final String dsUsername = "kt_cloud8888";
    private final String dsPassword = "Kt.cloud1234!@#$";

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testCreateCodeProject() {
        CodeProjectCreateCmd cmd = new CodeProjectCreateCmd();
        String groupId = "com.ark.app.shop2";
        String artifactId = "ark-app-shop2";
        String packageName = "com.ark.app.shop2";
        String description = "应用服务-商城";
        cmd.setName(description);
        cmd.setCode(artifactId);
        cmd.setReposSource(ReposSourceEnums.CREATE_NEW.getValue());
        cmd.setType(ProjectBasic.Type.BACKEND.getValue());
        cmd.setScaffold(ProjectBasic.Scaffold.SpringCloud.getValue());
        cmd.setDescription(description);
        cmd.setDeleteTempFileAfterGen(true);
        cmd.setGenerateMode(GenerateModeEnums.SCAFFOLD.getValue());
        SpringCloudProjectGenerateParam javaProjectGenerateParam = new SpringCloudProjectGenerateParam();
        javaProjectGenerateParam.setArtifactId(artifactId);
        javaProjectGenerateParam.setGroupId(groupId);
        javaProjectGenerateParam.setPackageName(packageName);
        javaProjectGenerateParam.setDsUrl(dsUrl);
        javaProjectGenerateParam.setDsUsername(dsUsername);
        javaProjectGenerateParam.setDsPassword(dsPassword);
        javaProjectGenerateParam.setGenDAOCode(false);

        String extProperties = JSON.toJSONString(BeanUtil.beanToMap(javaProjectGenerateParam));
        cmd.setExtProperties(extProperties);
        iCodeProjectService.createCodeProject(cmd);
    }

    @Test
    public void genCodeProjectTemp() {
        File original = new File("/Users/chenjiawei/code/myself/ark-center-eop/ark-center-eop-service/src/main/resources/scaffold/java/{{artifactId}}/{{artifactId}}-dao");
        FileUtil.rename(original, "test", true);
    }

    @Test
    public void testGetEnums() {

    }
}