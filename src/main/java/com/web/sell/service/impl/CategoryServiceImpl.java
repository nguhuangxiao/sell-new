package com.web.sell.service.impl;

import com.web.sell.mapper.CategoryMapper;
import com.web.sell.model.Category;
import com.web.sell.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategory(Long sellerId, List<Integer> typeList) {

        HashMap<String, Object> condition = new HashMap<>(2);
        condition.put("sellerId", sellerId);
        condition.put("type", typeList);

        return categoryMapper.findCategoryByType(condition);
    }
}
