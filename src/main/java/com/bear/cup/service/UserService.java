package com.bear.cup.service;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;

public interface UserService {

    String login(UserLoginReq userLoginReq);

    String register(UserRegisterReq userRegisterReq);
}
