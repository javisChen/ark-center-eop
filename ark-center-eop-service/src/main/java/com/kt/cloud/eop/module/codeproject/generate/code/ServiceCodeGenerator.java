package com.ark.center.eop.module.codeproject.generate.code;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ServiceCodeGenerator extends AbstractCodeGenerator {

    @Override
    protected InjectionConfig getInjectionConfig() {
        HashMap<String, Object> customMap = new HashMap<>();
        customMap.put("disabledEntity", true);
        customMap.put("module", true);
        return new InjectionConfig.Builder()
                .customMap(customMap)
                .build();

    }

    @Override
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig.Builder()
                .service("")
                .entity("")
                .mapper("")
                .xml("")
                .build();
    }


}