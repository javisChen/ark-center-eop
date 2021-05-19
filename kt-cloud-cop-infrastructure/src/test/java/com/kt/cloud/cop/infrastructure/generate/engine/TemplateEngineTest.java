package com.kt.cloud.cop.infrastructure.generate.engine;

import cn.hutool.core.io.FileUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TemplateEngineTest {

    private TemplateEngine templateEngine = new BeetlTemplateEngine();
    private GroupTemplate groupTemplate;
    private final StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();

    @Before
    public void before() {

        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.groupTemplate = new GroupTemplate(resourceLoader, cfg);
    }

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("comment", "工程类型 enums[BACKEND,后端应用,1 ;FRONTEND,前端应用,2]");
        String temp = FileUtil.readUtf8String("/Users/chenjiawei/code/myself/kt-cloud-cop/kt-cloud-cop-infrastructure/src/main/resources/templates/entity.java2.btl");;
        Template t = groupTemplate.getTemplate(temp);
        t.binding(map);
        System.out.println(t.render());
    }

}