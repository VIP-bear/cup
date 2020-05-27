package com.bear.cup.service;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;
import com.bear.cup.domain.User;
import org.springframework.data.domain.Page;

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

    /**
     * 分页查找全部用户
     * @return
     */
    Page<User> findAllUser(int page, int size);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);
}
