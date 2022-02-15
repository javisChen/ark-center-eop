package com.kt.cloud.eop.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "biz-module.git")
@Data
@Configuration
public class GitProperties {

    private String email;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;

}
