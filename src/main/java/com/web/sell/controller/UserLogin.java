package com.web.sell.controller;

import com.web.sell.model.User;
import com.web.sell.req.UserReg;
import com.web.sell.service.UserService;
import com.web.sell.util.Res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description: 买家登录
 * @Author: nguhuangxiao
 * @Date: 2018/12/17
 */
@RestController
@RequestMapping("/user")
public class UserLogin {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Res<?> login(@Valid UserReg userReg, BindingResult bindingResult) {
        //校验参数
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            String validMsg = fieldError.getDefaultMessage();
            return Res.buildFail(validMsg);
        }

        User user = userService.login(userReg.getPhone());
        if(user != null){

        }

        return null;
    }

}
