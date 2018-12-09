package com.web.sell.controller;

import com.web.sell.model.Category;
import com.web.sell.model.Product;
import com.web.sell.req.ProductReq;
import com.web.sell.service.CategoryService;
import com.web.sell.service.ProductService;
import com.web.sell.util.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(description = "商品相关接口")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Res<?> list(@Valid ProductReq productReq) {

        //List<Category> categoryList = categoryService.findAll(); //查询所有类目

        List<Product> productUpList = productService.findUpAll(productReq.getId());

        List<Integer> categoryList = new ArrayList<>();

        for(int i=0; i<productUpList.size(); i++) {



        }


        return null;
    }

}
