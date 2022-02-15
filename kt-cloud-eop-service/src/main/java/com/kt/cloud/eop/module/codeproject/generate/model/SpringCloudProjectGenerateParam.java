package com.kt.cloud.eop.module.codeproject.generate.model;

import lombok.Data;

@Data
public class SpringCloudProjectGenerateParam  {

    private String artifactId;
    private String groupId;
    private String packageName;
    private String dsUrl;
    private String dsUsername;
    private String dsPassword;
    private Boolean genDAOCode = false;
    private String[] include;

}
