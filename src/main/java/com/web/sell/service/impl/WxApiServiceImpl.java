package com.web.sell.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.web.sell.mapper.WxAccessTokenMapper;
import com.web.sell.model.WxAccessToken;
import com.web.sell.property.WxProp;
import com.web.sell.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class WxApiServiceImpl implements WxApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxProp wxProp;

    @Autowired(required = false)
    private WxAccessTokenMapper wxAccessTokenMapper;

    private final static String WX_API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private final static String  WX_API_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 获取token信息
     * @return
     */
    public Boolean testAccessToken(WxAccessToken list) {
        /*long currentTime = System.currentTimeMillis() / 1000;
        int tokenTime = list.getCreateTime() + 7200;
        if(currentTime < tokenTime) {
            return true;
        }else{
            return false;
        }*/
        return false;
    }

    @Override
    public String updateAccessToken() {
        //最新的access_token
        WxAccessToken list = wxAccessTokenMapper.findLatest();
        if(list != null) {
            //验证access_token
            if(testAccessToken(list)) {
                return list.getToken();
            }
        }
        System.out.println("test");
        String url = String.format(
            WX_API_ACCESS_TOKEN,
            wxProp.getAppId(),
            wxProp.getAppSecret()
        );
        String resultStr = restTemplate.getForObject(url, String.class);
        JSONObject rtnObj = JSONObject.parseObject(resultStr);
        WxAccessToken wxAccessToken = new WxAccessToken();

        wxAccessToken.setToken(rtnObj.getString("access_token"));
        wxAccessToken.setExpiresIn(rtnObj.getInteger("expires_in"));
        wxAccessToken.setAppId(wxProp.getAppId());
        wxAccessToken.setAppSecret(wxProp.getAppSecret());
        wxAccessToken.setCreateTime(new Date());

        return wxAccessToken.getToken();
    }

    @Override
    public void getTemplateMessage(String openId) {
        String url = "";
        url = WX_API_TEMPLATE_MESSAGE + openId;

        String resultStr = restTemplate.getForObject(url, String.class);
        System.out.print(url);
        System.out.print(resultStr);

    }

    @Override
    public WxAccessToken findLatest() {
        return wxAccessTokenMapper.findLatest();
    }
}
