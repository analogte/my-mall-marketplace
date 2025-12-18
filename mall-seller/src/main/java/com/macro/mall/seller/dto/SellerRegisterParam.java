package com.macro.mall.seller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
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
    private Double commissionRate;

    @ApiModelProperty(value = "Shop Logo URL")
    private String logo;
}
