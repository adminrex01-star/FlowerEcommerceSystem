package com.flower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // 这个注解让Spring Boot启动并扫描整个com.flower包
public class OnlineFlowerShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineFlowerShopApplication.class, args);
    }
}