package com.ark.center.eop.module.codeproject.dto.cmd;

import com.ark.center.eop.module.codeproject.dto.enums.GenerateModeEnums;
import com.ark.center.eop.module.codeproject.dto.enums.ReposSourceEnums;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 工程创建
 */
@Data
public class CodeProjectCreateCmd {

    /**
     * 工程名称
     */
    @NotEmpty(message = "工程名称不能为空")
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
     * 仓库来源
     * @see ReposSourceEnums
     */
    private Integer reposSource;

    /**
     * 源码生成方式
     * @see GenerateModeEnums
     */
    private Integer generateMode;

    /**
     * 生成完成后删除临时目录
     */
    private Boolean deleteTempFileAfterGen = true;
}
