package com.kt.cloud.eop.module.codeproject.generate.code;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DaoCodeGenerator extends AbstractCodeGenerator {

    @Override
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig.Builder()
                // 模板设置为空就不会生成
                .service("")
                .controller("")
                .serviceImpl("")
                .build();
    }

    @Override
    protected InjectionConfig getInjectionConfig() {
        HashMap<String, Object> customMap = new HashMap<>();
        customMap.put("disabledDto", true);
        return new InjectionConfig.Builder()
                .customMap(customMap)
                .build();

    }

}