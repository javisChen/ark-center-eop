package com.kt.cloud.cop.module.codeproject.generate.code;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.kt.cloud.cop.module.codeproject.generate.model.CodeGenerateModel;
import org.springframework.stereotype.Component;

@Component
public class DAOCodeGenerator {

    private final String superEntityClass = "com.kt.component.db.base.BaseEntity";
    private final String superControllerClass = "com.kt.component.web.base.BaseController";
    private final String[] superEntityColumns = new String[] {"id", "gmt_create", "gmt_modified", "creator", "modifier"};
    private final String author = "COP";

    public static void main(String[] args) {
        CodeGenerateModel model = new CodeGenerateModel();
        model.setUrl("jdbc:mysql://localhost:3306/cop?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&serverTimezone=UTC");
        model.setUsername("root");
        model.setPassword("Root1234!@#$");
        model.setOutputDir("/Users/chenjiawei/code/myself/kt-cloud-cop/kt-cloud-cop-dao/src/main/java");
        model.setParent("com.kt.cloud.cop.dao");
        new DAOCodeGenerator().execute(model);
    }

    public void execute(CodeGenerateModel model) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        mpg.setGlobalConfig(getGlobalConfig(model));

        // 数据源配置
        mpg.setDataSource(getDataSourceConfig(model));

        // 包配置
        PackageConfig pc = getPackageConfig(model);
        mpg.setPackageInfo(pc);

        // 配置模板
        mpg.setTemplate(getTemplateConfig());

        // 策略配置
        mpg.setStrategy(getStrategyConfig(pc, model));

        // 设置模板引擎
        mpg.setTemplateEngine(getTemplateEngine());
        mpg.execute();
    }

    private BeetlTemplateEngine getTemplateEngine() {
        return new BeetlTemplateEngine();
    }

    private TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        return templateConfig;
    }

    private GlobalConfig getGlobalConfig(CodeGenerateModel model) {
        GlobalConfig gc = new GlobalConfig();
        gc.setBaseResultMap(true);
        gc.setOutputDir(model.getOutputDir());
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }

    private StrategyConfig getStrategyConfig(PackageConfig pc, CodeGenerateModel model) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(superEntityClass);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(superControllerClass);
        strategy.setSuperEntityColumns(superEntityColumns);
        if (ArrayUtil.isNotEmpty(model.getInclude())) {
            strategy.setInclude(model.getInclude());
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        return strategy;
    }

    private PackageConfig getPackageConfig(CodeGenerateModel model) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(model.getParent());
        packageConfig.setController("");
//        packageConfig.setModuleName();
//        packageConfig.setEntity();
//        packageConfig.setService();
//        packageConfig.setServiceImpl();
//        packageConfig.setMapper();
//        packageConfig.setXml();
//        packageConfig.setPathInfo();
        return packageConfig;
    }

    private DataSourceConfig getDataSourceConfig(CodeGenerateModel model) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbQuery(new MySqlQuery());
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        dataSourceConfig.setKeyWordsHandler(new MySqlKeyWordsHandler());
        dataSourceConfig.setUrl(model.getUrl());
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(model.getUsername());
        dataSourceConfig.setPassword(model.getPassword());
        return dataSourceConfig;
    }

}