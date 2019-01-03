package com.web.sell.mapper;


import com.web.sell.model.User;

public interface UserMapper {

    User findUserByPhone(String phone);

}
