package com.bear.cup.dao.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * 订单表
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {

    /**
     * 订单id,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 商品id
     */
    @Column
    private Long product_id;

    /**
     * 商品名称
     */
    @Column
    private String product_name;

    /**
     * 交易时间
     */
    @Column
    private Long order_time;

    /**
     * 购买商品数量
     */
    @Column
    private int product_num;

    /**
     * 购买商品单价
     */
    @Column
    private double product_price;

    /**
     * 购买商品总价
     */
    @Column
    private double total_price;
}
