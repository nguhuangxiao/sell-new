package com.web.sell.service;


import com.web.sell.dto.WxResDto;
import com.web.sell.model.WxAccessToken;
import org.springframework.web.multipart.MultipartFile;

public interface WxDialogueService {

    /** 最新记录 **/
    WxAccessToken findLatest();

    String updateAccessToken();

    /** 模版消息 **/
    WxResDto getTemplateMessage();

    /** 菜单创建 **/
    WxResDto createMenu();

    /** 新增永久素材=> image、voice、video、thumb **/
    void addMaterial(MultipartFile file);

    /** 新增永久图文素材 **/
    void addNews();

}
