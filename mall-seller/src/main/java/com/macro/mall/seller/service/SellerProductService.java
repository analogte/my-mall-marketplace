package com.macro.mall.seller.service;

import com.macro.mall.model.PmsProduct;
import com.macro.mall.seller.dto.SellerProductParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Seller Product Service Interface
 */
public interface SellerProductService {
    /**
     * Create a new product for the current seller
     */
    @Transactional
    int create(SellerProductParam productParam);

    /**
     * List products for the current seller
     */
    List<PmsProduct> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * Get product by ID (with shop ownership check)
     */
    PmsProduct getById(Long id);

    /**
     * Update product (with shop ownership check)
     */
    @Transactional
    int update(Long id, SellerProductParam productParam);

    /**
     * Soft delete products (with shop ownership check)
     */
    @Transactional
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * Update publish status (with shop ownership check)
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * Update new status (with shop ownership check)
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * Update recommend status (with shop ownership check)
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
}
