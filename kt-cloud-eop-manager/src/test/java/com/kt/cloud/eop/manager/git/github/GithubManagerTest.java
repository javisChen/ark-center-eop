package com.kt.cloud.eop.manager.git.github;

import com.kt.cloud.eop.manager.git.GitCreateReposResponse;
import com.kt.cloud.eop.manager.git.config.GithubConfiguration;
import com.kt.cloud.eop.manager.git.github.request.GithubCreateReposRequest;
import org.junit.Test;

public class GithubManagerTest {

    @Test
    public void createRepos() {
        GithubConfiguration githubConfiguration = new GithubConfiguration();
        String authorization = "Basic amF2aXNDaGVuOmdocF80Mzdod2NDakpiQnBhZEZvN3lSQloyQXNoQUlMUzIzRnVZN2o=";
        githubConfiguration.setAuthorization(authorization);
        GithubManager githubManager = new GithubManager(githubConfiguration);
        GithubCreateReposRequest request = new GithubCreateReposRequest();
        request.setName("cloudTest6");
        request.setDescription("cloudTest5");
        GitCreateReposResponse repos = githubManager.createRepos(request);
        System.out.println(repos);
    }
}