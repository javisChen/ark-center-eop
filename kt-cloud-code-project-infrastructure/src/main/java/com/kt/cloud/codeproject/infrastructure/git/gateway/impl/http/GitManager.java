package com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http;

import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.request.GiteeCreateReposRequest;
import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.request.GiteeGetTokenRequest;
import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.response.GiteeCreateReposResponse;
import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.response.GiteeGetTokenResponse;

public interface GitManager {

    GiteeCreateReposResponse createRepos(GiteeCreateReposRequest request);

    GiteeGetTokenResponse getToken(GiteeGetTokenRequest req);
}
