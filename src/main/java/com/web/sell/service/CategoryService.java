package com.web.sell.service;

import com.web.sell.model.Category;

import java.util.List;


public interface CategoryService {

    /** 商品类型查询 **/
    List<Category> findAll();

}
