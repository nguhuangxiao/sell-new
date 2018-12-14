package com.web.sell.service.impl;

import com.web.sell.constant.ProductStatus;
import com.web.sell.dto.StockDto;
import com.web.sell.mapper.CategoryMapper;
import com.web.sell.mapper.ProductMapper;
import com.web.sell.model.Product;
import com.web.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findUpAll(Long sellerId) {

        HashMap<String, Object> condition = new HashMap<>(2);
        condition.put("sellerId", sellerId);
        condition.put("status", ProductStatus.UP);
        return productMapper.selectByStatusUp(condition);

    }

    @Override
    public void addStock(StockDto stockDto) {

    }

    @Override
    public void decreaseStock(StockDto stockDto) {

    }
}
