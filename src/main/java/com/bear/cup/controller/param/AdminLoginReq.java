package com.bear.cup.controller.param;

import lombok.Data;

@Data
public class AdminLoginReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
