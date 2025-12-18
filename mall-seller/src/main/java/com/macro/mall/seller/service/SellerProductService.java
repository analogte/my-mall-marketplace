package com.macro.mall.seller.service;

import com.macro.mall.seller.dto.SellerProductParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * Seller Product Service Interface
 */
public interface SellerProductService {
    /**
     * Create a new product for the current seller
     */
    @Transactional
    int create(SellerProductParam productParam);
}
