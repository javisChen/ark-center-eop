package com.kt.cloud.cop.infrastructure.generate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JavaProjectGenerateParam extends ProjectGenerateParam {

    private String artifactId;
    private String groupId;
    private String packageName;
    private String dsUrl;
    private String dsUsername;
    private String dsPassword;
    private Boolean genDAOCode = false;
    private String[] include;

}
