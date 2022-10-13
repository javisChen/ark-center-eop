package com.ark.center.eop.module.codeproject.generate.project;

import java.io.File;
import java.util.Map;

public interface ProjectGenerator {

    File generator(Map<String, Object> extProperties);
}