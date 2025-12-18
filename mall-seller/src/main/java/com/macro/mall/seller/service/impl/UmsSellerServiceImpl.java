package com.macro.mall.seller.service.impl;

import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import com.macro.mall.seller.dto.SellerRegisterParam;
import com.macro.mall.seller.service.UmsSellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsSellerServiceImpl implements UmsSellerService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(SellerRegisterParam sellerRegisterParam) {
        // 1. Check if username exists
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(sellerRegisterParam.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            Asserts.fail("Username already exists");
        }

        // 2. Create User (Using UmsAdmin table for Auth currently, ideally should
        // separate)
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(sellerRegisterParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

        // Encode password
        String encodedPassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodedPassword);
        adminMapper.insert(umsAdmin);

        // 3. Create Shop Profile (TODO: Needs generated Mapper for UmsShop)
        // Since we haven't run MBG to generate UmsShopMapper yet, we will mark this as
        // TODO.
        // In a real flow, here we would:
        // UmsShop shop = new UmsShop();
        // shop.setOwnerId(umsAdmin.getId());
        // shop.setShopName(sellerRegisterParam.getShopName());
        // shopMapper.insert(shop);
    }

    @Override
    public String login(String username, String password) {
        // TODO: Implement JWT Login Logic
        return null;
    }
}
