package com.kt.cloud.eop.module.codeproject.service;

import com.kt.cloud.eop.module.codeproject.generate.code.DaoCodeGenerator;
import com.kt.cloud.eop.module.codeproject.generate.code.ServiceCodeGenerator;
import com.kt.cloud.eop.module.codeproject.generate.model.CodeGenerateModel;
import org.junit.jupiter.api.Test;

public class DaoCodeTest {

    private final String dsUrl = "jdbc:mysql://gz-cynosdbmysql-grp-irl7x9ar.sql.tencentcdb.com:20716/pay?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True";
    private final String dsUsername = "kt_cloud8888";
    private final String dsPassword = "Kt.cloud1234!@#$";
//    private final String daoOutputDir = "D:\\code\\javis\\kt-cloud-commodity\\kt-cloud-commodity-dao\\src\\main\\java";
    private final String daoOutputDir = "C:\\Code\\kt\\kt-cloud-pay\\kt-cloud-pay-dao\\src\\main\\java";
//private final String serviceOutputDir = "D:\\code\\javis\\kt-cloud-commodity\\kt-cloud-commodity-service\\src\\main\\java";
    private final String serviceOutputDir = "C:\\Code\\kt\\kt-cloud-pay\\kt-cloud-pay-service\\src\\main\\java";
    private final String[] include = {
            "pa_pay_notify_record",
            "pa_pay_order",
            "pa_pay_type",
    };

    @Test
    public void testGenDaoCode() {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl(dsUrl);
        model.setUsername(dsUsername);
        model.setPassword(dsPassword);
        model.setOutputDir(daoOutputDir);
        model.setParent("com.kt.cloud.pay.dao");
        model.setTablePrefix(new String[]{"pa_"});
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
        model.setParent("com.kt.cloud.pay.module");
        model.setTablePrefix(new String[]{"pa_"});
        model.setInclude(include);
        new ServiceCodeGenerator().execute(model);
    }
}
