package com.web.sell.model;


import lombok.Data;

import java.util.Date;

@Data
public class Category {

    private Long gid;

    private Long sellerId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}