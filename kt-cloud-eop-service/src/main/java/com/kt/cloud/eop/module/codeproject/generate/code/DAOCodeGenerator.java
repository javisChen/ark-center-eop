package com.kt.cloud.eop.module.codeproject.generate.code;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.kt.cloud.eop.module.codeproject.generate.model.CodeGenerateModel;
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
        model.setOutputDir("/Users/chenjiawei/code/myself/kt-cloud-eop/kt-cloud-eop-dao/src/main/java");
        model.setParent("com.kt.cloud.eop.dao");
        new DAOCodeGenerator().execute(model);
    }

    public void execute(CodeGenerateModel model) {
        AutoGenerator mpg = new AutoGenerator(getDataSourceConfig(model));
        PackageConfig packageConfig = getPackageConfig(model);
        mpg.global(getGlobalConfig(model))
                .packageInfo(packageConfig)
                .strategy(getStrategyConfig(packageConfig, model))
                .template(getTemplateConfig());
        mpg.execute(getTemplateEngine());
    }

    private BeetlTemplateEngine getTemplateEngine() {
        return new BeetlTemplateEngine();
    }

    private TemplateConfig getTemplateConfig() {
        return new TemplateConfig.Builder()
                .controller("")
                .build();
    }

    private GlobalConfig getGlobalConfig(CodeGenerateModel model) {
        return new GlobalConfig.Builder()
                .outputDir(model.getOutputDir())
                .author(author)
                .disableOpenDir()
                .fileOverride()
                .build();
    }

    private StrategyConfig getStrategyConfig(PackageConfig packageConfig, CodeGenerateModel model) {
        return new StrategyConfig.Builder()
                .addInclude(model.getInclude())
                .addTablePrefix(packageConfig.getModuleName() + "_")
                .controllerBuilder()
                    .enableHyphenStyle()
                    .superClass(superControllerClass)
                    .enableRestStyle()
                .entityBuilder()
                    .naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .enableLombok()
                    .superClass(superEntityClass)
                    .addSuperEntityColumns(superEntityColumns)
                .build();
    }

    private PackageConfig getPackageConfig(CodeGenerateModel model) {
        return new PackageConfig.Builder()
                .parent(model.getParent())
                .controller("")
                .build();
    }

    private DataSourceConfig getDataSourceConfig(CodeGenerateModel model) {
        return new DataSourceConfig.Builder(model.getUrl(), model.getUsername(), model.getPassword())
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler()).build();
    }

}