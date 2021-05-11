package com.kt.cloud.codeproject.infrastructure.git.gateway.impl;

import com.kt.cloud.codeproject.domain.git.gateway.GitGateway;
import com.kt.cloud.codeproject.domain.git.model.Git;
import com.kt.cloud.codeproject.infrastructure.git.convertor.GitConvertor;
import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.GitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitGatewayImpl implements GitGateway {

    @Autowired
    private GitManager gitManager;

    @Override
    public void createGitRepos(Git git) {
        gitManager.createRepos(GitConvertor.toRequestForCreate(git));
    }
}
