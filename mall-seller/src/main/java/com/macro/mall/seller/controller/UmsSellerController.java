package com.macro.mall.seller.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.seller.dto.SellerRegisterParam;
import com.macro.mall.seller.service.UmsSellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "UmsSellerController", description = "Seller Authentication Management")
@RequestMapping("/seller")
public class UmsSellerController {

    @Autowired
    private UmsSellerService sellerService;

    @ApiOperation(value = "Seller Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody SellerRegisterParam sellerLoginParam) {
        String token = sellerService.login(sellerLoginParam.getUsername(), sellerLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("Login failed");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "Bearer ");
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "Seller Register")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestBody SellerRegisterParam sellerRegisterParam) {
        sellerService.register(sellerRegisterParam);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "Get current seller info")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getInfo() {
        // Mock data for now, real implementation should retrieve from SecurityContext
        Map<String, Object> data = new HashMap<>();
        data.put("username", "Seller");
        data.put("roles", new String[] { "TEST" });
        data.put("icon", "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg");
        return CommonResult.success(data);
    }

    @ApiOperation(value = "Logout")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
