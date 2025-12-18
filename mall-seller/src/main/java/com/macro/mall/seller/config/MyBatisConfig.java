package com.macro.mall.seller.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({ "com.macro.mall.mapper", "com.macro.mall.seller.mapper" })
public class MyBatisConfig {
}
