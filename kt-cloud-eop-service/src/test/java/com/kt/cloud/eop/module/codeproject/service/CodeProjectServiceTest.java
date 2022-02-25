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
import com.kt.cloud.eop.module.codeproject.generate.code.DAOCodeGenerator;
import com.kt.cloud.eop.module.codeproject.generate.model.CodeGenerateModel;
import com.kt.cloud.eop.module.codeproject.generate.model.SpringCloudProjectGenerateParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
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
        String groupId = "com.kt.cloud";
        String artifactId = "kt-cloud-order13";
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
        javaProjectGenerateParam.setDsUrl(dsUrl);
        javaProjectGenerateParam.setDsUsername(dsUsername);
        javaProjectGenerateParam.setDsPassword(dsPassword);
        javaProjectGenerateParam.setGenDAOCode(false);

        String extProperties = JSON.toJSONString(BeanUtil.beanToMap(javaProjectGenerateParam));
        cmd.setExtProperties(extProperties);
        System.out.println(JSONObject.toJSONString(cmd));
        iCodeProjectService.createCodeProject(cmd);
    }

    @Test
    public void testGenDaoCode() {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl(dsUrl);
        model.setUsername(dsUsername);
        model.setPassword(dsPassword);
        model.setOutputDir("/Users/chenjiawei/code/myself/kt-cloud/kt-cloud-commodity/kt-cloud-commodity-dao/src/main/java");
        model.setParent("com.kt.cloud.commodity.dao");
        new DAOCodeGenerator().execute(model);

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