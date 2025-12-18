package com.macro.mall.seller.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;

public class SellerRegisterParam {
    @ApiModelProperty(value = "Username", required = true)
    @NotEmpty
    private String username;

    @ApiModelProperty(value = "Password", required = true)
    @NotEmpty
    private String password;

    @ApiModelProperty(value = "Shop Name", required = true)
    @NotEmpty
    private String shopName;

    @ApiModelProperty(value = "Platform Commission Rate (0.0 - 1.0)")
    private BigDecimal commissionRate;

    @ApiModelProperty(value = "Shop Logo URL")
    private String logo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
