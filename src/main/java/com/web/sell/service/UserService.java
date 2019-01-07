package com.web.sell.service;


import com.web.sell.model.User;
import com.web.sell.req.UserReg;

public interface UserService {

    User login(UserReg userReg);

}
