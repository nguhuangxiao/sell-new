package com.web.sell.service;

import com.web.sell.dto.StockDto;
import com.web.sell.model.Product;
import java.util.List;


public interface ProductService {

    /** 查询在架商品 **/
    List<Product> findUpAll(Long sellerId);

    /** 增加库存 **/
    void addStock(StockDto stockDto);

    /** 减库存 **/
    void decreaseStock(StockDto stockDto);

}
