package com.macro.mall.seller.controller;

import com.macro.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "UmsSellerController", description = "Seller Authentication Management")
@RequestMapping("/seller")
public class UmsSellerController {

    @ApiOperation(value = "Seller Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "Seller Register")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register() {
        return CommonResult.success(null);
    }
}
