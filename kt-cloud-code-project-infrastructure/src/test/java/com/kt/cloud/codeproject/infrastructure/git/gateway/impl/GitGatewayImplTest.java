package com.kt.cloud.codeproject.infrastructure.git.gateway.impl;

import com.kt.cloud.codeproject.domain.git.gateway.GitGateway;
import com.kt.cloud.codeproject.domain.git.model.Git;
import org.junit.Test;

public class GitGatewayImplTest {

    private GitGateway gateway = new GitGatewayImpl();

    @Test
    public void createGitGateway() {
        Git git = new Git();
        git.setName("cloudTest6");
        git.setDescription("cloudTest5");
        git.setAccessToken("6b968e86bb95da1fa20391af889013f1");
        gateway.createGitRepos(git);
    }
}