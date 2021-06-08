package com.kt.cloud.cop.client.codeproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeProjectListVO {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String type;

    private String scaffold;

    private String gitReposUrl;

    private LocalDateTime createTime;

}
