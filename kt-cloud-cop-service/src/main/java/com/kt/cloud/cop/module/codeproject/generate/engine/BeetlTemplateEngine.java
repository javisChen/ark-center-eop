package com.kt.cloud.cop.module.codeproject.generate.engine;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Component
public class BeetlTemplateEngine implements TemplateEngine, InitializingBean {

    private GroupTemplate groupTemplate;
    private final StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();

    @Override
    public String render(Map<String, Object> params, String template) {
        Template t = groupTemplate.getTemplate(template);
        t.binding(params);
        return t.render();
    }

    @Override
    public void renderTo(Map<String, Object> params, String template, OutputStream outputStream) {
        Template t = groupTemplate.getTemplate(template);
        t.binding(params);
        t.renderTo(outputStream);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            cfg.setPlaceholderStart("{{");
            cfg.setPlaceholderEnd("}}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.groupTemplate = new GroupTemplate(resourceLoader, cfg);
    }
}
