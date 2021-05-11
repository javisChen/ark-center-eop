package com.kt.cloud.codeproject.infrastructure.generate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JavaProjectGenerateParam extends ProjectGenerateParam {

    private String artifactId;
    private String groupId;
    private String packageName;

}
