package com.macro.mall.seller.service.impl;

import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import com.macro.mall.seller.mapper.UmsShopMapper;
import com.macro.mall.seller.model.UmsShop;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.seller.bo.SellerUserDetails;
import com.macro.mall.seller.dto.SellerRegisterParam;
import com.macro.mall.seller.service.UmsSellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private UmsShopMapper shopMapper;

    @Override
    public void register(SellerRegisterParam sellerRegisterParam) {
        // 1. Check if username exists
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(sellerRegisterParam.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            Asserts.fail("Username already exists");
        }

        // 2. Create User (Using UmsAdmin table for Auth currently)
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(sellerRegisterParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

        // Encode password
        String encodedPassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodedPassword);
        adminMapper.insert(umsAdmin);

        // 3. Create Shop Profile
        UmsShop shop = new UmsShop();
        shop.setOwnerId(umsAdmin.getId());
        shop.setShopName(sellerRegisterParam.getShopName());
        shop.setCommissionRate(sellerRegisterParam.getCommissionRate());
        shop.setLogo(sellerRegisterParam.getLogo());
        shop.setCreateTime(new Date());
        shop.setStatus(1); // Default active
        shopMapper.insert(shop);
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("Password incorrect");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("Account disabled");
            }
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            // Log warning
        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return new SellerUserDetails(adminList.get(0));
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
