package com.web.sell.controller;

import com.web.sell.service.WxApiService;
import com.web.sell.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxApiController {

    @Autowired
    private WxApiService wxApiService;

    @RequestMapping(value = "/updateToken", method = RequestMethod.GET)
    public Res<?> updateToken() {
        wxApiService.updateAccessToken();
        return null;
    }

    @RequestMapping(value = "/templateMessage", method = RequestMethod.GET)
    public Res<?> templateMessage(@RequestParam String openId) {
        //wxApiService.getAccessToken();
        //wxApiService.getTemplateMessage(openId);
        return null;
    }
}
