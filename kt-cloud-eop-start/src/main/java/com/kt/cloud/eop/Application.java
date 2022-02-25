package com.kt.cloud.eop;

import com.kt.component.web.config.CloudAppConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.kt.cloud.**")
@MapperScan("com.kt.cloud.eop.dao.mapper")
@EnableDiscoveryClient
public class Application extends CloudAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
