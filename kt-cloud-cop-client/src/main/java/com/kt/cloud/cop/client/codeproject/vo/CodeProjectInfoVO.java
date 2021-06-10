package com.kt.cloud.cop.client.codeproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeProjectInfoVO {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String type;

    private String scaffold;

    private String gitReposUrl;

    private String reposStatus;

    private String pushStatus;

}
