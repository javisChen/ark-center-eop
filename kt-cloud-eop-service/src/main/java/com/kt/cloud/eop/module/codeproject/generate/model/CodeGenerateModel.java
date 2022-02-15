package com.kt.cloud.eop.module.codeproject.generate.model;

import lombok.Data;

@Data
public class CodeGenerateModel {

    private String url;
    private String username;
    private String password;
    private String outputDir;
    private String parent;
    private String[] include;
    /**
     * 生成dao层基础代码
     */
    private Boolean genDaoCode = true;
}
