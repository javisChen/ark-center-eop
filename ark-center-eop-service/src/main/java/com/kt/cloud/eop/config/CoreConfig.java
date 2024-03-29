package com.ark.center.eop.config;

import com.ark.center.eop.manager.git.config.GithubConfiguration;
import com.ark.center.eop.manager.git.github.GithubManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {


//    @Bean
//    public GitManager gitManager() {
//        return new GiteeManager();
//    }

    @Bean
    public GithubManager gitManager(GithubConfiguration githubConfiguraion) {
        return new GithubManager(githubConfiguraion);
    }
}
