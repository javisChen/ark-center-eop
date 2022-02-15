package com.kt.cloud.cop.manager.git.gitee.request;

import lombok.Data;

@Data
public class GiteeGetTokenRequest {

    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;
}
