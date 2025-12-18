package com.macro.mall.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.macro.mall")
public class MallSellerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSellerApplication.class, args);
    }
}
