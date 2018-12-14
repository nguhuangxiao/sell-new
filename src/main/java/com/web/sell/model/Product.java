package com.web.sell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    private Long gid;

    private Long sellerId;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String icon;

    private Byte status;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}