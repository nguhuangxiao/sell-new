package com.web.sell.service.impl;

import com.web.sell.mapper.UserMapper;
import com.web.sell.model.User;
import com.web.sell.req.UserReg;
import com.web.sell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserReg userReg) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = userReg.getPassword() + "fengshi";
        User user = userMapper.findUserByPhone(userReg.getPhone());
        if(user != null){
            if(encoder.matches(encode, user.getPassword())) {
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
