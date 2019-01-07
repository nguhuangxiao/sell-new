package com.web.sell.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private long gid;

    private String name;

    private String password;

    private String phone;

    private Integer sex;

    private String openId;

    private String icon;

    private Date createTime;

}
