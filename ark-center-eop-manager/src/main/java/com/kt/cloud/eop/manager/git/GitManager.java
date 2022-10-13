package com.ark.center.eop.manager.git;

import com.ark.center.eop.manager.git.gitee.request.GiteeGetTokenRequest;
import com.ark.center.eop.manager.git.gitee.response.GiteeGetTokenResponse;

public interface GitManager {

    GitCreateReposResponse createRepos(GitCreateReposRequest req);

    GiteeGetTokenResponse getToken(GiteeGetTokenRequest req);
}
