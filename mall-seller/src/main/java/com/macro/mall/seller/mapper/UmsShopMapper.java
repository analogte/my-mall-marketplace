package com.macro.mall.seller.mapper;

import com.macro.mall.seller.model.UmsShop;
import com.macro.mall.seller.model.UmsShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopMapper {
    long countByExample(UmsShopExample example);

    int deleteByExample(UmsShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsShop row);

    int insertSelective(UmsShop row);

    List<UmsShop> selectByExample(UmsShopExample example);

    UmsShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsShop row, @Param("example") UmsShopExample example);

    int updateByExample(@Param("row") UmsShop row, @Param("example") UmsShopExample example);

    int updateByPrimaryKeySelective(UmsShop row);

    int updateByPrimaryKey(UmsShop row);
}