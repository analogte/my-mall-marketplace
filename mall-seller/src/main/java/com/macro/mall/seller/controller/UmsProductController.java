package com.macro.mall.seller.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.seller.dto.SellerProductParam;
import com.macro.mall.seller.service.SellerProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult<Integer> create(@RequestBody SellerProductParam productParam) {
        int count = sellerProductService.create(productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("List Products")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = sellerProductService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("Get Product Detail")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProduct> getById(@PathVariable Long id) {
        PmsProduct product = sellerProductService.getById(id);
        return CommonResult.success(product);
    }

    @ApiOperation("Update Product")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id,
            @RequestBody SellerProductParam productParam) {
        int count = sellerProductService.update(id, productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("Delete Product (Soft)")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateDeleteStatus(@RequestParam("ids") List<Long> ids,
            @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = sellerProductService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("Update Publish Status")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updatePublishStatus(@RequestParam("ids") List<Long> ids,
            @RequestParam("publishStatus") Integer publishStatus) {
        int count = sellerProductService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("Update New Status")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateNewStatus(@RequestParam("ids") List<Long> ids,
            @RequestParam("newStatus") Integer newStatus) {
        int count = sellerProductService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("Update Recommend Status")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateRecommendStatus(@RequestParam("ids") List<Long> ids,
            @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = sellerProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
