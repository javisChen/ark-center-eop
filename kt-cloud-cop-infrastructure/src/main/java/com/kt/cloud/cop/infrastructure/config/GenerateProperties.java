package com.kt.cloud.cop.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "biz-module.generate")
@Data
@Configuration
public class GenerateProperties {

    private String tempDir;

}
