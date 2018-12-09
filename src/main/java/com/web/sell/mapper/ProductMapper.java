package com.web.sell.mapper;

import com.web.sell.model.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductMapper {

    int deleteByPrimaryKey(Long gid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long gid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByStatusUp(HashMap<String, Object> condition);

}