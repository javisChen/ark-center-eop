package com.kt.cloud.codeproject.generate;

import com.kt.cloud.codeproject.generate.model.ProjectGenerateParam;

public interface ProjectGenerator<T extends ProjectGenerateParam> {

    boolean generator(T param);
}
