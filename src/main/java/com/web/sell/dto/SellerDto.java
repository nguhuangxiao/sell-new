package com.web.sell.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: nguhuangxiao
 * @Date: 2019/1/15
 */
@Data
public class SellerDto {

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

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
