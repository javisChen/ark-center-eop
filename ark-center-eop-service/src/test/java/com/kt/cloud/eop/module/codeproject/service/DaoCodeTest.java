package com.ark.center.eop.module.codeproject.service;

import com.ark.center.eop.module.codeproject.generate.code.DaoCodeGenerator;
import com.ark.center.eop.module.codeproject.generate.code.ServiceCodeGenerator;
import com.ark.center.eop.module.codeproject.generate.model.CodeGenerateModel;
import org.junit.jupiter.api.Test;

public class DaoCodeTest {

        private final String dsUrl = "jdbc:mysql://gz-cynosdbmysql-grp-irl7x9ar.sql.tencentcdb.com:20716/order?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC&allowPublicKeyRetrieval=True";
    private final String dsUsername = "kt_cloud8888";
    private final String dsPassword = "Kt.cloud1234!@#$";
//    private final String dsUrl = "jdbc:oracle:thin:@localhost:1521:XE";
//    private final String dsUsername = "system";
//    private final String dsPassword = "root";
//    private final String daoOutputDir = "D:\\code\\javis\\ark-center-commodity\\ark-center-commodity-dao\\src\\main\\java";
    private final String daoOutputDir = "C:\\Code\\kt\\ark-center-order\\ark-center-order-dao\\src\\main\\java";
//private final String serviceOutputDir = "D:\\code\\javis\\ark-center-commodity\\ark-center-commodity-service\\src\\main\\java";
    private final String serviceOutputDir = "C:\\Code\\kt\\ark-center-order\\ark-center-order-service\\src\\main\\java";
    private final String[] include = {
            "od_cart_item",
    };

    @Test
    public void testGenDaoCode() {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl(dsUrl);
        model.setUsername(dsUsername);
        model.setPassword(dsPassword);
        model.setOutputDir(daoOutputDir);
        model.setParent("com.ark.center.order.dao");
        model.setTablePrefix(new String[]{"od_"});
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
        model.setParent("com.ark.center.order.module");
        model.setTablePrefix(new String[]{"od_"});
        model.setInclude(include);
        new ServiceCodeGenerator().execute(model);
    }
}
