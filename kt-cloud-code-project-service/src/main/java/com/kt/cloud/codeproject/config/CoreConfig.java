package com.kt.cloud.codeproject.config;

import com.kt.cloud.codeproject.manager.git.GitManager;
import com.kt.cloud.codeproject.manager.git.gitee.GiteeManager;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public GitManager gitManager() {
        return new GiteeManager();
    }
}
