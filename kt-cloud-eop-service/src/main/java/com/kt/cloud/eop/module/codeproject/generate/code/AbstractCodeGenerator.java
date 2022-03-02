package com.kt.cloud.eop.module.codeproject.generate.code;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.kt.cloud.eop.module.codeproject.generate.model.CodeGenerateModel;
import com.kt.component.db.base.BaseEntity;
import com.kt.component.web.base.BaseController;

public class AbstractCodeGenerator {

    private final String[] superEntityColumns = new String[] {"id", "gmt_create", "gmt_modified", "creator", "modifier"};
    protected final String author = "EOP";

    public void execute(CodeGenerateModel model) {
        AutoGenerator mpg = new AutoGenerator(getDataSourceConfig(model));
        PackageConfig packageConfig = getPackageConfig(model);
        mpg.global(getGlobalConfig(model))
                .injection(getInjectionConfig())
                .packageInfo(packageConfig)
                .strategy(getStrategyConfig(packageConfig, model))
                .template(getTemplateConfig());
        mpg.execute(getTemplateEngine());
    }

    protected InjectionConfig getInjectionConfig() {
        return null;
    }

    private BeetlTemplateEngine getTemplateEngine() {
        return new BeetlTemplateEngine();
    }

    protected TemplateConfig getTemplateConfig() {
        return null;
    }

    protected GlobalConfig getGlobalConfig(CodeGenerateModel model) {
        return new GlobalConfig.Builder()
                .outputDir(model.getOutputDir())
                .author(author)
                .enableSwagger()
                .disableOpenDir()
                .build();
    }

    private StrategyConfig getStrategyConfig(PackageConfig packageConfig, CodeGenerateModel model) {
        StrategyConfig.Builder strategyBuilder = new StrategyConfig.Builder()
                .addTablePrefix(packageConfig.getModuleName() + "_");
        if (ArrayUtil.isNotEmpty(model.getInclude())) {
            strategyBuilder.addInclude(model.getInclude());
        }
        Entity.Builder builder = strategyBuilder
                .serviceBuilder()
                    .fileOverride()
                    .formatServiceImplFileName("%sService")
                .controllerBuilder()
                    .fileOverride()
                    .superClass(BaseController.class)
                    .enableHyphenStyle()
                    .enableRestStyle()
                .entityBuilder()
                    .fileOverride()
                    .naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .enableLombok()
                    .superClass(BaseEntity.class)
                    .addSuperEntityColumns(superEntityColumns);
        return builder
                .build();
    }

    private PackageConfig getPackageConfig(CodeGenerateModel model) {
        return new PackageConfig.Builder()
                .serviceImpl("service")
                .parent(model.getParent())
                .build();
    }

    private DataSourceConfig getDataSourceConfig(CodeGenerateModel model) {
        return new DataSourceConfig.Builder(model.getUrl(), model.getUsername(), model.getPassword())
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler()).build();
    }

}