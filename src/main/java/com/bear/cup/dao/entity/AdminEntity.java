package com.bear.cup.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 管理员表
 */

@Data
@Entity
@Table(name = "t_admin")
public class AdminEntity {

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

}
