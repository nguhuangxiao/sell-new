package com.web.sell.service;


import com.web.sell.dto.WxResDto;
import com.web.sell.model.WxAccessToken;

public interface WxDialogueService {

    /** 最新记录 **/
    WxAccessToken findLatest();

    String updateAccessToken();

    /** 模版消息 **/
    WxResDto getTemplateMessage();

    /** 菜单创建 **/
    WxResDto createMenu();

}
