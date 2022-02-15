package com.kt.cloud.eop.module.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitReposInfo {

    private String reposUrl;
    private String reposName;
}
