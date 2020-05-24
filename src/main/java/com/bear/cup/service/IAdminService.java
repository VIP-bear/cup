package com.bear.cup.service;

import com.bear.cup.controller.param.AdminLoginReq;

public interface IAdminService {

    /**
     * 管理员登陆
     * @param adminLoginReq
     * @return
     */
    String login(AdminLoginReq adminLoginReq);

}
