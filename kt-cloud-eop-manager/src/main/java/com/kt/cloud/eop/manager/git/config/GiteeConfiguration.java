package com.kt.cloud.eop.manager.git.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "biz-module.git.gitee")
@Data
@Configuration
public class GiteeConfiguration {

    private String email;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String accessToken;

}
