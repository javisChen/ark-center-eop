package com.kt.cloud.codeproject.manager.git;

import com.kt.cloud.codeproject.manager.git.gitee.request.GiteeCreateReposRequest;
import com.kt.cloud.codeproject.manager.git.gitee.response.GiteeCreateReposResponse;

public interface GitManager {

    GiteeCreateReposResponse createRepos(GiteeCreateReposRequest request);
}
