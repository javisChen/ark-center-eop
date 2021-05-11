package com.kt.cloud.codeproject.infrastructure.git.convertor;

import com.kt.cloud.codeproject.domain.git.model.Git;
import com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.request.GiteeCreateReposRequest;

public class GitConvertor {

    public static GiteeCreateReposRequest toRequestForCreate(Git git) {
        GiteeCreateReposRequest giteeCreateReposRequest = new GiteeCreateReposRequest();
        giteeCreateReposRequest.setName(git.getName());
        giteeCreateReposRequest.setDescription(git.getDescription());
        return giteeCreateReposRequest;
    }
}
