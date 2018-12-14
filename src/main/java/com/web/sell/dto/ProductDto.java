package com.web.sell.dto;

import com.web.sell.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long gid;

    private String name;

    private Integer type;

    private List<Product> list;

}
