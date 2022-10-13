package com.ark.center.eop.module.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitReposInfo {

    private String sshUrl;
    private String reposName;
}
