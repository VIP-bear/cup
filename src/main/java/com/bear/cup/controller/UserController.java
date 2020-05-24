package com.bear.cup.controller;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;
import com.bear.cup.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 */

@Slf4j
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     * @param userLoginReq
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody UserLoginReq userLoginReq){
        log.info("UserLoginReq : {}", userLoginReq);
        String loginMessage = userService.login(userLoginReq);
        log.info("Login User:{}", loginMessage);
        return loginMessage;
    }

    /**
     * 用户注册
     * @param userRegisterReq
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterReq userRegisterReq){
        log.info("userRegisterReq : {}", userRegisterReq);
        String registerMessage = userService.register(userRegisterReq);
        log.info("Register User:{}", registerMessage);
        return registerMessage;
    }
}
