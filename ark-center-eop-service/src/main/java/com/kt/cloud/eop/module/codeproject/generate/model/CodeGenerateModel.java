package com.ark.center.eop.module.codeproject.generate.model;

import lombok.Data;

@Data
public class CodeGenerateModel {

    private String url;
    private String username;
    private String password;
    private String outputDir;
    private String parent;
    private String[] include;
    private String[] tablePrefix;
    /**
     * 生成dao层基础代码
     */
    private Boolean genDaoCode = true;
}
