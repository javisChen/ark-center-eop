package com.kt.cloud.codeproject.infrastructure.generate;

import com.kt.cloud.codeproject.infrastructure.generate.model.ProjectGenerateParam;

import java.io.File;

public interface ProjectGenerator<T extends ProjectGenerateParam> {

    File generator(T param);
}