package com.kt.cloud.codeproject.client.git.api;

import com.kt.cloud.codeproject.client.git.dto.command.GitReposCreateCmd;

public interface IGitService {

    void createRepository(GitReposCreateCmd cmd);
}
