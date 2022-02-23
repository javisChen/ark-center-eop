package com.kt.cloud.eop.manager.git.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "biz-module.git.github")
@Data
@Configuration
public class GithubConfiguration {

    private String authorization;

}
