package com.bear.cup.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 购物车表
 */

@Data
@Entity
@Table(name = "t_cart")
public class CartEntity {

    /**
     * 购物车id,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cart_id;

    /**
     * 商品id
     */
    @Column
    private Long product_id;

    /**
     * 商品名
     */
    @Column
    private String product_name;

    /**
     * 商品图片链接
     */
    @Column
    private String product_pic_url;

    /**
     * 商品数量
     */
    @Column
    private int product_num;

    /**
     * 商品单价
     */
    @Column
    private double product_price;

    /**
     * 商品总价
     */
    @Column
    private double total_price;

    /**
     * 用户名
     */
    @Column
    private String username;


}
