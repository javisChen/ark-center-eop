package com.kt.cloud.codeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = { "com.kt.cloud.codeproject.**"})
@ComponentScans(value = {@ComponentScan("com.kt.cloud.codeproject.**")})
//@MapperScan("com.example.demo.gatewayimpl.database")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
