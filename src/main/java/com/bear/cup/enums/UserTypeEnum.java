package com.bear.cup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户登录、注册等状态
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    LOGIN_SUCCESS(120, "登录成功"),
    LOGIN_FAILED(121, "登录失败"),
    USER_NOT_EXIST(122, "用户不存在"),
    PASSWORD_NOT_CORRECT(123, "密码不正确"),
    REGISTER_SUCCESS(124, "注册成功"),
    REGISTER_FAILED(125, "注册失败"),
    USER_EXISTED(126, "用户已存在"),
    INCONSISTENT_PASSWORD_TWICE(127, "两次密码不一致")
    ;

    private Integer code;
    private String message;

}
