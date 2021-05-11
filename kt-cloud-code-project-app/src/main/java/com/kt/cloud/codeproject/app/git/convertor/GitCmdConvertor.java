package com.kt.cloud.codeproject.app.git.convertor;

import com.kt.cloud.codeproject.client.git.dto.command.GitReposCreateCmd;
import com.kt.cloud.codeproject.domain.git.model.Git;

public class GitCmdConvertor {

    public static Git toDomainObject(GitReposCreateCmd cmd) {
        Git git = new Git();
        git.setName(cmd.getName());
        git.setDescription(cmd.getDescription());
        return git;
    }
}
