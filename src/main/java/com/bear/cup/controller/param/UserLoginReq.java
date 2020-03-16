package com.bear.cup.controller.param;

import lombok.Data;

@Data
public class UserLoginReq {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
