package com.kt.cloud.eop.module.codeproject.service;

import com.kt.cloud.eop.module.codeproject.generate.code.DaoCodeGenerator;
import com.kt.cloud.eop.module.codeproject.generate.code.ServiceCodeGenerator;
import com.kt.cloud.eop.module.codeproject.generate.model.CodeGenerateModel;
import org.junit.jupiter.api.Test;

public class DaoCodeTest {

    private final String dsUrl = "jdbc:mysql://gz-cynosdbmysql-grp-irl7x9ar.sql.tencentcdb.com:20716/commodity?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True";
    private final String dsUsername = "kt_cloud8888";
    private final String dsPassword = "Kt.cloud1234!@#$";
//    private final String daoOutputDir = "D:\\code\\javis\\kt-cloud-commodity\\kt-cloud-commodity-dao\\src\\main\\java";
    private final String daoOutputDir = "/Users/chenjiawei/code/myself/kt-cloud/kt-cloud-commodity/kt-cloud-commodity-dao/src/main/java";
//private final String serviceOutputDir = "D:\\code\\javis\\kt-cloud-commodity\\kt-cloud-commodity-service\\src\\main\\java";
    private final String serviceOutputDir = "/Users/chenjiawei/code/myself/kt-cloud/kt-cloud-commodity/kt-cloud-commodity-service/src/main/java";
    String[] include = {
            "co_attachment",
    };


    @Test
    public void testGenDaoCode() {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl(dsUrl);
        model.setUsername(dsUsername);
        model.setPassword(dsPassword);
        model.setOutputDir(daoOutputDir);
        model.setParent("com.kt.cloud.commodity.dao");
        model.setTablePrefix(new String[]{"co_"});
        model.setInclude(include);
        new DaoCodeGenerator().execute(model);
    }

    @Test
    public void testGenModuleCode() {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl(dsUrl);
        model.setUsername(dsUsername);
        model.setPassword(dsPassword);
        model.setOutputDir(serviceOutputDir);
        model.setParent("com.kt.cloud.commodity.module");
        model.setTablePrefix(new String[]{"co_"});
        model.setInclude(include);
        new ServiceCodeGenerator().execute(model);
    }
}
