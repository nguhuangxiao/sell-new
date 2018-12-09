package com.web.sell.service.impl;

import com.web.sell.mapper.SellerMapper;
import com.web.sell.model.Seller;
import com.web.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public Seller findOne(Long id) {
        return sellerMapper.selectByPrimaryKey(id);
    }
}
