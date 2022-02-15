package com.kt.cloud.eop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.kt.cloud.**")
@MapperScan("com.kt.cloud.cop.dao.mapper")
@EnableDiscoveryClient
public class CopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopApplication.class, args);
    }

}
