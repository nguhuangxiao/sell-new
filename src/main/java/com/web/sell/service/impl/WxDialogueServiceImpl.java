package com.web.sell.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.sell.dto.WxResDto;
import com.web.sell.mapper.WxAccessTokenMapper;
import com.web.sell.model.WxAccessToken;
import com.web.sell.property.WxProp;
import com.web.sell.service.WxDialogueService;
import com.web.sell.util.WxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;


@Service
public class WxDialogueServiceImpl implements WxDialogueService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxProp wxProp;

    @Autowired(required = false)
    private WxAccessTokenMapper wxAccessTokenMapper;

    private final static String WX_API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private final static String  WX_API_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    private final static String WX_API_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    private final static String WX_API_ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";

    private final static String WX_API_ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=";

    @Override
    public WxAccessToken findLatest() {
        return wxAccessTokenMapper.findLatest();
    }

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
        obj.put("touser", "ofYhr1HwyrE43O7GcQkENe16QSyY");
        obj.put("template_id", "Qy0aSdwVGCm2yGUxUYFwokZV6_47iEfCVNZEAJf6KKE");
        obj.put("topcolor", "#FF0000");
        JSONObject data = new JSONObject();

        JSONObject userData = new JSONObject();
        userData.put("value", "尊敬的用户");
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
        keyword4Data.put("value", "货物已到达北京网点，待派件");
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
    public WxResDto createMenu() {
        String token = updateAccessToken();
        String url = WX_API_CREATE_MENU + token;

        JSONObject obj = new JSONObject();

        JSONArray arr = new JSONArray();
        //一级菜单
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "今日歌曲");
        jsonObject.put("type", "click");
        jsonObject.put("key", "JQiI99_2OvMmv9jbJ-Go8B6gtXRPnSzvyLVz4k40jGE");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "菜单");

        //二级菜单
        JSONObject twolevelObj1 = new JSONObject();
        twolevelObj1.put("type", "view");
        twolevelObj1.put("name", "搜索");
        twolevelObj1.put("url", "http://www.soso.com/");

        JSONObject twolevelObj2 = new JSONObject();
        twolevelObj2.put("type", "miniprogram");
        twolevelObj2.put("name", "丰实跟单");
        twolevelObj2.put("url", "http://mp.weixin.qq.com");
        twolevelObj2.put("appid", "wxfbfbd2bc5978b601");
        twolevelObj2.put("pagepath", "pages/project/list");

        JSONObject twolevelObj3 = new JSONObject();
        twolevelObj3.put("type", "click");
        twolevelObj3.put("name", "赞一下我们");
        twolevelObj3.put("key", "V1001_GOOD");

        JSONArray twolevelMenu = new JSONArray();
        twolevelMenu.add(twolevelObj1);
        twolevelMenu.add(twolevelObj2);
        twolevelMenu.add(twolevelObj3);

        jsonObject1.put("sub_button", twolevelMenu);

        arr.add(jsonObject);
        arr.add(jsonObject1);

        obj.put("button", arr);

        String resultStr = restTemplate.postForObject(url, obj, String.class);
        //处理返回结果
        WxResDto wxResDto = WxRes.cb(resultStr);
        return wxResDto;

    }

    @Override
    public void addMaterial(MultipartFile file) {
        String token = updateAccessToken();
        String url = String.format(
            WX_API_ADD_MATERIAL,
            token,
            "thumb"
        );
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("media", file.getResource());
        params.add("name", "media");
        params.add("filename", file.getName());
        String resultStr = restTemplate.postForObject(url, params, String.class);
        System.out.println(resultStr);
    }

    @Override
    public void addNews() {
        String token = updateAccessToken();
        String url = WX_API_ADD_NEWS + token;

        JSONObject params = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject obj1 = new JSONObject();
        obj1.put("title", "前端最该了解的十片文章");
        obj1.put("thumb_media_id", "JQiI99_2OvMmv9jbJ-Go8PnNg-Rd-Oh8ygnrwcqagSc");
        obj1.put("author", "huangxiao");
        obj1.put("digest", "前端最该了解的十片文章");
        obj1.put("show_cover_pic", 1);
        obj1.put("content", "本次 Ethereum 君士坦丁堡升级是 Ethereum 由大都会转向宁静前的最后一次升级，升级采取的硬分叉模式，为了防止用户在升级时转账出现问题，我们决定暂时关闭 FOD 通道。由于在 2019 年 01 月 16 日凌晨，Ethereum 君士坦丁堡版本被曝出安全漏洞因此 FOD 通道重启只能延期，重启日期需要根据 Ethereum 基金会对这次安全漏洞对处理结果待定。");
        obj1.put("content_source_url", "https://segmentfault.com/a/1190000017911885");
        obj1.put("need_open_comment", 1);
        obj1.put("only_fans_can_comment", 0);
        arr.add(obj1);
        params.put("articles", arr);

        String resultStr = restTemplate.postForObject(url, params, String.class);
        System.out.println(resultStr);

    }
}
