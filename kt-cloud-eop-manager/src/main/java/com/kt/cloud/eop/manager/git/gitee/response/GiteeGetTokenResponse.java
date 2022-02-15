package com.kt.cloud.eop.manager.git.gitee.response;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GiteeGetTokenResponse {

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "token_type")
    private String tokenType;
    @JSONField(name = "expires_in")
    private int expiresIn;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "scope")
    private String scope;
    @JSONField(name = "created_at")
    private int createdAt;

    public static GiteeGetTokenResponse fromString(String s) {
        return JSONObject.parseObject(s, GiteeGetTokenResponse.class);
    }
}
