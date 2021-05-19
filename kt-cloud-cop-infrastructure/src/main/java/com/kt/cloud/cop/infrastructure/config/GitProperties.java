package com.kt.cloud.cop.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "git")
@Data
@Configuration
public class GitProperties {

    private String email;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;

}
