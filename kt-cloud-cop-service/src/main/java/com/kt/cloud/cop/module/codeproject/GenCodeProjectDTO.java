package com.kt.cloud.cop.module.codeproject;

import lombok.Data;

@Data
public class GenCodeProjectDTO {

    private String name;
    private String description;
    private String type;
    private String extProperties;

    /**
     * 创建Git仓库
     */
    private Boolean createGitRepos = false;

    /**
     * 生成完成后删除临时目录
     */
    private Boolean deleteTempFileAfterGen = true;
}
