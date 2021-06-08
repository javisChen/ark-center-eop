package com.kt.cloud.cop.manager.git.gitee;


import com.kt.cloud.cop.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.cop.manager.git.gitee.response.GiteeCreateReposResponse;
import org.junit.jupiter.api.Test;

public class GiteeManagerTest {

    @Test
    public void testCreateRepos() {
        GiteeManager giteeManager = new GiteeManager();
        GiteeCreateReposRequest request = new GiteeCreateReposRequest();
        request.setName("cloudTest6");
        request.setDescription("cloudTest5");
        request.setAccessToken("6b968e86bb95da1fa20391af889013f1");
        GiteeCreateReposResponse repos = giteeManager.createRepos(request);
    }
}