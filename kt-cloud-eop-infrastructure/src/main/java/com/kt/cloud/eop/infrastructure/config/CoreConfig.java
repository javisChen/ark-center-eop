package com.kt.cloud.cop.infrastructure.config;

import com.kt.cloud.cop.manager.git.GitManager;
import com.kt.cloud.cop.manager.git.gitee.GiteeManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public GitManager gitManager() {
        return new GiteeManager();
    }
}
