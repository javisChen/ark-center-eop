package com.kt.cloud.codeproject.infrastructure.git.gateway.impl.http.response;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GiteeGetTokenResponse {


    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("created_at")
    private int createdAt;

    public static GiteeGetTokenResponse fromString(String s) {
        return JSONObject.parseObject(s, GiteeGetTokenResponse.class);
    }
}
