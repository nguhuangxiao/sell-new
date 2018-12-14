package com.web.sell.mapper;

import com.web.sell.model.Category;

import java.util.HashMap;
import java.util.List;

public interface CategoryMapper {

    int deleteByPrimaryKey(Long gid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long gid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> findCategoryByType(HashMap<String, Object> condition);

}