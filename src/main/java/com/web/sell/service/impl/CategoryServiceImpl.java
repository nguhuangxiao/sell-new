package com.web.sell.service.impl;

import com.web.sell.mapper.CategoryMapper;
import com.web.sell.model.Category;
import com.web.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
}
