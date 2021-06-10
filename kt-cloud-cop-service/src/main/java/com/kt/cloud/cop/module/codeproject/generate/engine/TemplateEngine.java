package com.kt.cloud.cop.module.codeproject.generate.engine;

import java.io.OutputStream;
import java.util.Map;

public interface TemplateEngine {

    String render(Map<String, Object> params, String template);

    void renderTo(Map<String, Object> params, String template, OutputStream outputStream);
}
