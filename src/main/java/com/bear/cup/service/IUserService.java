package com.bear.cup.service;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;

public interface IUserService {

    /**
     * 用户登录
     * @param userLoginReq
     * @return
     */
    String login(UserLoginReq userLoginReq);

    /**
     * 用户注册
     * @param userRegisterReq
     * @return
     */
    String register(UserRegisterReq userRegisterReq);
}
