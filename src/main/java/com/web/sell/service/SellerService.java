package com.web.sell.service;

import com.web.sell.model.Seller;
import com.web.sell.req.SellerReq;

import java.util.List;

public interface SellerService {

    Seller findOne(Long id);

    List<Seller> listByPage(SellerReq sellerReq);
}
