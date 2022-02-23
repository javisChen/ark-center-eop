package com.kt.cloud.eop.manager.git;

import com.kt.cloud.eop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.kt.cloud.eop.manager.git.gitee.response.GiteeGetTokenResponse;

public interface GitManager {

    GitCreateReposResponse createRepos(GitCreateReposRequest req);

    GiteeGetTokenResponse getToken(GiteeGetTokenRequest req);
}
