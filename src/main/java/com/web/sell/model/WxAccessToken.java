package com.web.sell.model;

import lombok.Data;


@Data
public class WxAccessToken {

    private Long gid;

    private String token;

    private String appId;

    private String appSecret;

    private int expiresIn;

    private int createTime;

}
