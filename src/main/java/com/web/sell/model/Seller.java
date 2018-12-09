package com.web.sell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Seller {

    private Long gid;

    private String name;

    private String icon;

    private String notice;

    private String description;

    private Integer deliveryTime;

    private BigDecimal sellScore;

    private BigDecimal minPrice;

    private BigDecimal deliveryPrice;

    private BigDecimal packingPrice;

    private Date createTime;

    private Date updateTime;

}