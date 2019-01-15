package com.web.sell.service;


import com.web.sell.dto.WxResDto;
import com.web.sell.model.WxAccessToken;

public interface WxApiService {

    String updateAccessToken();

    /** 模版消息 **/
    WxResDto getTemplateMessage();

    /** 最新记录 **/
    WxAccessToken findLatest();

}
