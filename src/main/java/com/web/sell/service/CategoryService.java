package com.web.sell.service;

import com.web.sell.model.Category;

import java.util.List;


public interface CategoryService {

    /**
     * 查找商品类型
     * @param sellerId
     * @param typeList
     * @return
     */
    List<Category> findCategory(Long sellerId, List<Integer> typeList);

}
