package com.kt.cloud.cop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = {"com.kt.cloud.cop.**"})
@ComponentScans(value = {@ComponentScan("com.kt.cloud.cop.**")})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
