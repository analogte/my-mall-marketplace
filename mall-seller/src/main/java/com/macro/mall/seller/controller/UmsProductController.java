package com.macro.mall.seller.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.seller.dto.SellerProductParam;
import com.macro.mall.seller.service.SellerProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Seller Product Management Controller
 */
@Controller
@Api(tags = "UmsProductController", description = "Seller Product Management")
@RequestMapping("/product")
public class UmsProductController {
    @Autowired
    private SellerProductService sellerProductService;

    @ApiOperation("Create Product")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SellerProductParam productParam) {
        int count = sellerProductService.create(productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
