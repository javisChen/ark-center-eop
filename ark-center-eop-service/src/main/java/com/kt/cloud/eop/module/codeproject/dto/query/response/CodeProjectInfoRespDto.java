package com.ark.center.eop.module.codeproject.dto.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeProjectInfoRespDto {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String type;

    private String scaffold;

    private String gitHtmlUrl;

    private String gitHttpsUrl;

    private String gitSshUrl;

    private String reposStatus;

    private String pushStatus;

}
