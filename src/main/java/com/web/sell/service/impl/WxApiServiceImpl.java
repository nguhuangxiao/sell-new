package com.web.sell.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.web.sell.dto.WxResDto;
import com.web.sell.mapper.WxAccessTokenMapper;
import com.web.sell.model.WxAccessToken;
import com.web.sell.property.WxProp;
import com.web.sell.service.WxApiService;
import com.web.sell.util.TimeUtil;
import com.web.sell.util.WxRes;
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
        long timeStamp = list.getCreateTime().getTime() + (long)7200000;
        long currentTime = System.currentTimeMillis();
        //token时间超过2个小时，自动刷新
        System.out.println(timeStamp + "-----" + currentTime);
        if(currentTime > timeStamp){
            return false;
        }else{
            return true;
        }
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
        wxAccessTokenMapper.insert(wxAccessToken);
        return wxAccessToken.getToken();
    }

    @Override
    public WxResDto getTemplateMessage() {
        //查询最新的token
        String token = updateAccessToken();
        String url = WX_API_TEMPLATE_MESSAGE + token;

        JSONObject obj = new JSONObject();
        obj.put("touser", "ofYhr1D7H7UEYHTEqhpqvndXM4Ro");
        obj.put("template_id", "Qy0aSdwVGCm2yGUxUYFwokZV6_47iEfCVNZEAJf6KKE");
        obj.put("topcolor", "#FF0000");
        JSONObject data = new JSONObject();

        JSONObject userData = new JSONObject();
        userData.put("value", "尊敬的黄潇用户");
        userData.put("color", "#173177");

        JSONObject keyword1Data = new JSONObject();
        keyword1Data.put("value", "KY282301");
        keyword1Data.put("color", "#173177");

        JSONObject keyword2Data = new JSONObject();
        keyword2Data.put("value", "石油");
        keyword2Data.put("color", "#173177");

        JSONObject keyword3Data = new JSONObject();
        keyword3Data.put("value", "2019-1-15 20:18:20");
        keyword3Data.put("color", "#173177");

        JSONObject keyword4Data = new JSONObject();
        keyword4Data.put("value", "货物已到达北京网点，待派活");
        keyword4Data.put("color", "#173177");

        JSONObject remarkData = new JSONObject();
        remarkData.put("value", "感谢您的支持，祝您生活愉快");
        remarkData.put("color", "#173177");

        data.put("first", userData);
        data.put("keyword1", keyword1Data);
        data.put("keyword2", keyword2Data);
        data.put("keyword3", keyword3Data);
        data.put("keyword4", keyword4Data);
        data.put("remark", remarkData);
        obj.put("data", data);

        String resultStr = restTemplate.postForObject(url, obj, String.class);
        //处理返回结果
        WxResDto wxResDto = WxRes.cb(resultStr);
        return wxResDto;
    }

    @Override
    public WxAccessToken findLatest() {
        return wxAccessTokenMapper.findLatest();
    }
}
