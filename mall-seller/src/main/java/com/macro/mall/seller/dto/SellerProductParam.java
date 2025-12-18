package com.macro.mall.seller.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * Product Creation Parameter
 */
public class SellerProductParam {
    @ApiModelProperty(value = "Product Name", required = true)
    @NotEmpty(message = "Product name is required")
    private String name;

    @ApiModelProperty(value = "Sub-title")
    private String subTitle;

    @ApiModelProperty(value = "Description")
    private String description;

    @ApiModelProperty(value = "Product Price", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "Original Price")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "Stock Quantity", required = true)
    private Integer stock;

    @ApiModelProperty(value = "Product SN")
    private String productSn;

    @ApiModelProperty(value = "Product Image URL")
    private String pic;

    @ApiModelProperty(value = "Product Category ID")
    private Long productCategoryId;

    @ApiModelProperty(value = "Brand ID")
    private Long brandId;

    @ApiModelProperty(value = "Publish Status: 0->Off shelf; 1->On shelf")
    private Integer publishStatus;

    @ApiModelProperty(value = "Sort Order")
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
