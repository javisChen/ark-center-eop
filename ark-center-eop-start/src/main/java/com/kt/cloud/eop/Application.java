package com.ark.center.eop;

import com.ark.component.web.config.CloudAppConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.ark.center.**")
@MapperScan("com.ark.center.eop.dao.mapper")
@EnableDiscoveryClient
public class Application extends CloudAppConfig {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
