package com.web.sell.mapper;

import com.web.sell.model.Seller;

public interface SellerMapper {

    int deleteByPrimaryKey(Long gid);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Long gid);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}