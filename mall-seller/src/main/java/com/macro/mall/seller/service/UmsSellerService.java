package com.macro.mall.seller.service;

import com.macro.mall.seller.dto.SellerRegisterParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * Seller Management Service Interface
 */
public interface UmsSellerService {
    /**
     * Register a new seller/merchant
     */
    @Transactional
    void register(SellerRegisterParam sellerRegisterParam);

    /**
     * Seller Login
     * Return JWT Token
     */
    String login(String username, String password);

    /**
     * Load User by Username
     */
    UserDetails loadUserByUsername(String username);
}
