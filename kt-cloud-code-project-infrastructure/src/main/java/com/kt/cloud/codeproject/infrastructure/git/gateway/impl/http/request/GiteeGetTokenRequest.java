package com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class GiteeGetTokenRequest {

    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;
}
