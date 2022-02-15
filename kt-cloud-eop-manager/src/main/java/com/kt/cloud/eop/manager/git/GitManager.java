package com.kt.cloud.cop.manager.git;

import com.kt.cloud.cop.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.cop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.cop.manager.git.gitee.response.GiteeCreateReposResponse;
import com.kt.cloud.cop.manager.git.gitee.response.GiteeGetTokenResponse;

public interface GitManager {

    GiteeCreateReposResponse createRepos(GiteeCreateReposRequest request);

    GiteeGetTokenResponse getToken(GiteeGetTokenRequest req);
}
