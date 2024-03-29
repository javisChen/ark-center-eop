package com.ark.center.eop.manager.git.gitee;


import com.ark.center.eop.manager.git.GitCreateReposResponse;
import com.ark.center.eop.manager.git.config.GiteeConfiguration;
import com.ark.center.eop.manager.git.gitee.request.GiteeCreateReposRequest;
import org.junit.jupiter.api.Test;

public class GiteeManagerTest {

    @Test
    public void testCreateRepos() {
        GiteeConfiguration giteeConfiguration = new GiteeConfiguration();
        GiteeManager giteeManager = new GiteeManager(giteeConfiguration);
        GiteeCreateReposRequest request = new GiteeCreateReposRequest();
        request.setName("cloudTest888");
        request.setDescription("cloudTest5");
        GitCreateReposResponse repos = giteeManager.createRepos(request);
    }
}