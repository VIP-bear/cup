package com.bear.cup.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户库表
 */
@Data
@Entity
@Table(name = "t_user")
public class UserEntity {
    /**
     * 用户id,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 用户密码
     */
    @Column
    private String password;

    /**
     * 用户昵称
     */
    @Column
    private String nickname;

    /**
     * 用户姓名
     */
    @Column
    private String name;

    /**
     * 用户性别
     * 0女性，1男性，2保密
     */
    @Column
    private Integer sex;

    /**
     * 用户地址
     */
    @Column
    private String address;

    /**
     * 用户手机
     */
    @Column
    private String phone;

    /**
     * 用户积分
     */
    @Column
    private Long integral;

    /**
     * 用户头像地址
     */
    @Column
    private String head_pic_url;

}
