package com.kt.cloud.eop.infrastructure.config;

import com.kt.cloud.eop.manager.git.GitManager;
import com.kt.cloud.eop.manager.git.gitee.GiteeManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public GitManager gitManager() {
        return new GiteeManager();
    }
}
