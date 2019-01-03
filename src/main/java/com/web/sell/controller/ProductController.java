package com.web.sell.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.sell.dto.ProductDto;
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

        //查询上架商品
        List<Product> productUpList = productService.findUpAll(productReq.getId());

        List<Integer> categoryTypeList = new ArrayList<>();
        for(Product product : productUpList) {
            categoryTypeList.add(product.getCategoryType());
        }

        //查询所有类目
        List<Category> categoryList = categoryService.findCategory(productReq.getId(), categoryTypeList);

        //数据拼接
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Category category : categoryList){

            ProductDto productDto = new ProductDto();
            productDto.setName(category.getCategoryName());
            productDto.setType(category.getCategoryType());

            List<Product> productList = new ArrayList<>();
            for(Product product : productUpList){
                Integer type = product.getCategoryType();
                //判断商品类型相同
                if(category.getCategoryType().equals(type)){
                    productList.add(product);
                }
            }
            productDto.setList(productList);
            productDtoList.add(productDto);
        }

        PageHelper.startPage(productReq.getPageNo(), productReq.getPageSize());
        //List<>
        PageInfo<ProductDto> page = new PageInfo<>(productDtoList);

        return Res.buildOk(page);
    }

}
