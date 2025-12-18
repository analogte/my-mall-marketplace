package com.macro.mall.seller.service.impl;

import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.seller.bo.SellerUserDetails;
import com.macro.mall.seller.dto.SellerProductParam;
import com.macro.mall.seller.mapper.UmsShopMapper;
import com.macro.mall.seller.model.UmsShop;
import com.macro.mall.seller.model.UmsShopExample;
import com.macro.mall.seller.service.SellerProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Seller Product Service Implementation
 */
@Service
public class SellerProductServiceImpl implements SellerProductService {
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private UmsShopMapper shopMapper;

    @Override
    public int create(SellerProductParam productParam) {
        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(productParam, product);

        // Get current seller info
        UmsAdmin currentSeller = getCurrentSeller();
        if (currentSeller == null) {
            throw new RuntimeException("No logged in user found");
        }

        // Get Shop ID
        UmsShopExample example = new UmsShopExample();
        example.createCriteria().andOwnerIdEqualTo(currentSeller.getId());
        List<UmsShop> shops = shopMapper.selectByExample(example);
        if (shops.isEmpty()) {
            throw new RuntimeException("Shop not found for this seller");
        }
        Long shopId = shops.get(0).getId();

        product.setShopId(shopId);

        // Set default values if null
        if (product.getId() == null) {
            product.setId(null); // Auto increment
        }
        if (product.getDeleteStatus() == null) {
            product.setDeleteStatus(0);
        }
        if (product.getNewStatus() == null) {
            product.setNewStatus(0);
        }
        if (product.getRecommandStatus() == null) {
            product.setRecommandStatus(0);
        }
        if (product.getVerifyStatus() == null) {
            product.setVerifyStatus(0);
        }

        return productMapper.insertSelective(product);
    }

    private UmsAdmin getCurrentSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SellerUserDetails) {
            return ((SellerUserDetails) authentication.getPrincipal()).getUmsAdmin();
        }
        return null;
    }
}
