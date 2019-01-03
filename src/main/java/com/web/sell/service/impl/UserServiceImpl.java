package com.web.sell.service.impl;

import com.web.sell.mapper.UserMapper;
import com.web.sell.model.User;
import com.web.sell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}
