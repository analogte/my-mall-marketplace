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

    @Override
    public List<PmsProduct> list(String keyword, Integer pageSize, Integer pageNum) {
        com.github.pagehelper.PageHelper.startPage(pageNum, pageSize);
        com.macro.mall.model.PmsProductExample example = new com.macro.mall.model.PmsProductExample();
        com.macro.mall.model.PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteStatusEqualTo(0);

        // Filter by Shop ID
        UmsAdmin currentSeller = getCurrentSeller();
        if (currentSeller != null) {
            UmsShopExample shopExample = new UmsShopExample();
            shopExample.createCriteria().andOwnerIdEqualTo(currentSeller.getId());
            List<UmsShop> shops = shopMapper.selectByExample(shopExample);
            if (!shops.isEmpty()) {
                criteria.andShopIdEqualTo(shops.get(0).getId());
            } else {
                return new java.util.ArrayList<>(); // No shop = no products
            }
        }

        if (keyword != null && !keyword.isEmpty()) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return productMapper.selectByExample(example);
    }

    /**
     * Helper method to get current seller's shop ID
     */
    private Long getCurrentShopId() {
        UmsAdmin currentSeller = getCurrentSeller();
        if (currentSeller == null) {
            throw new RuntimeException("No logged in user found");
        }
        UmsShopExample shopExample = new UmsShopExample();
        shopExample.createCriteria().andOwnerIdEqualTo(currentSeller.getId());
        List<UmsShop> shops = shopMapper.selectByExample(shopExample);
        if (shops.isEmpty()) {
            throw new RuntimeException("Shop not found for this seller");
        }
        return shops.get(0).getId();
    }

    /**
     * Verify that a product belongs to the current seller's shop
     */
    private boolean isProductOwnedBySeller(Long productId) {
        Long shopId = getCurrentShopId();
        PmsProduct product = productMapper.selectByPrimaryKey(productId);
        return product != null && shopId.equals(product.getShopId());
    }

    @Override
    public PmsProduct getById(Long id) {
        if (!isProductOwnedBySeller(id)) {
            throw new RuntimeException("Product not found or access denied");
        }
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SellerProductParam productParam) {
        // Security check: verify ownership
        if (!isProductOwnedBySeller(id)) {
            throw new RuntimeException("Product not found or access denied");
        }

        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(productParam, product);
        product.setId(id);
        // Keep the shop ID unchanged (don't allow changing shop)
        product.setShopId(null);

        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        Long shopId = getCurrentShopId();
        int count = 0;
        for (Long id : ids) {
            PmsProduct product = productMapper.selectByPrimaryKey(id);
            // Security check: only delete if owned by seller
            if (product != null && shopId.equals(product.getShopId())) {
                PmsProduct updateProduct = new PmsProduct();
                updateProduct.setId(id);
                updateProduct.setDeleteStatus(deleteStatus);
                count += productMapper.updateByPrimaryKeySelective(updateProduct);
            }
        }
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        Long shopId = getCurrentShopId();
        int count = 0;
        for (Long id : ids) {
            PmsProduct product = productMapper.selectByPrimaryKey(id);
            if (product != null && shopId.equals(product.getShopId())) {
                PmsProduct updateProduct = new PmsProduct();
                updateProduct.setId(id);
                updateProduct.setPublishStatus(publishStatus);
                count += productMapper.updateByPrimaryKeySelective(updateProduct);
            }
        }
        return count;
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        Long shopId = getCurrentShopId();
        int count = 0;
        for (Long id : ids) {
            PmsProduct product = productMapper.selectByPrimaryKey(id);
            if (product != null && shopId.equals(product.getShopId())) {
                PmsProduct updateProduct = new PmsProduct();
                updateProduct.setId(id);
                updateProduct.setNewStatus(newStatus);
                count += productMapper.updateByPrimaryKeySelective(updateProduct);
            }
        }
        return count;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        Long shopId = getCurrentShopId();
        int count = 0;
        for (Long id : ids) {
            PmsProduct product = productMapper.selectByPrimaryKey(id);
            if (product != null && shopId.equals(product.getShopId())) {
                PmsProduct updateProduct = new PmsProduct();
                updateProduct.setId(id);
                updateProduct.setRecommandStatus(recommendStatus);
                count += productMapper.updateByPrimaryKeySelective(updateProduct);
            }
        }
        return count;
    }
}
