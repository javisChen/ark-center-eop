package com.kt.cloud.cop.client.codeproject.cmd;

import lombok.Data;

/**
 * 工程创建
 */
@Data
public class CodeProjectCreateCmd {

    /**
     * 工程名称
     */
    private String name;

    /**
     * 工程代码
     */
    private String code;

    /**
     * 工程描述
     */
    private String description;

    /**
     * 工程类型
     */
    private Integer type;

    /**
     * 脚手架
     */
    private Integer scaffold;

    /**
     * 工程特殊属性
     */
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
