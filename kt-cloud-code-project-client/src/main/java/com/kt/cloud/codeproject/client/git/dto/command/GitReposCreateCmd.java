package com.kt.cloud.codeproject.client.git.dto.command;

import lombok.Data;

@Data
public class GitReposCreateCmd {

    private String name;
    private String description;
}
