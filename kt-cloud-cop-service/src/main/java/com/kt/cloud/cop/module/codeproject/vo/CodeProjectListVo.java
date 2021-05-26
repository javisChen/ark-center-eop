package com.kt.cloud.cop.module.codeproject.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeProjectListVo {

    private String name;

    private String code;

    private String description;

    private Integer type;

    private String gitReposUrl;
}
