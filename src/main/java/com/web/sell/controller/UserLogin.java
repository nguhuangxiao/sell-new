package com.web.sell.controller;

import com.alibaba.fastjson.JSON;
import com.web.sell.model.User;
import com.web.sell.req.UserReg;
import com.web.sell.service.UserService;
import com.web.sell.util.JwtTokenUtils;
import com.web.sell.util.Res;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static com.web.sell.constant.ResponceMessage.LOGIN_ERROR;

/**
 * @Description: 用户
 * @Author: nguhuangxiao
 * @Date: 2018/12/17
 */
@RestController
@RequestMapping("/user")
public class UserLogin {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Res<?> login(HttpServletRequest req, HttpServletResponse res, @RequestBody @Valid UserReg userReg, BindingResult bindingResult) {
        //校验参数
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            String validMsg = fieldError.getDefaultMessage();
            return Res.buildFail(validMsg);
        }
        User user = userService.login(userReg);
        if(user == null){
            return Res.buildFail(LOGIN_ERROR);
        }else{
            //存储session
            req.getSession().setAttribute("web_user_info", user);
            //返回数据清空密码
            user.setPassword(null);
            //对象序列化成json字符串
            String subject = JSON.toJSONString(user);
            Map<String, Object> payload = new HashMap<>(2);
            payload.put("phone", user.getPhone());
            payload.put("subject", subject);
            //生成token
            String token = JwtTokenUtils.createJwtToken(payload);
            HashMap<String, Object> result = new HashMap<>(2);
            result.put("user", user);
            result.put("token", token);
            return Res.buildOk(result);
        }

    }

    @ApiOperation(value = "注销")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Res<?> logout(HttpServletRequest req, HttpServletResponse res) {
        req.getSession().setAttribute("web_user_info", null);
        return Res.buildOk("");
    }
}
