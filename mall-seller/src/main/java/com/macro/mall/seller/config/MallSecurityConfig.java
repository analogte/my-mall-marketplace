package com.macro.mall.seller.config;

import com.macro.mall.security.component.DynamicSecurityService;
import com.macro.mall.seller.service.UmsSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.macro.mall.security.config.SecurityConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsSellerService sellerService;

    @Bean
    public UserDetailsService userDetailsService() {
        // Retrieve Login User Info
        return username -> sellerService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                // TODO: Implement Role-Based Access Control logic later
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                return map;
            }
        };
    }

}
