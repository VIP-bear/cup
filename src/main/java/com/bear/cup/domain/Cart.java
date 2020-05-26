package com.bear.cup.domain;

import lombok.Data;

/**
 * 返回给前端的购物车商品信息
 */

@Data
public class Cart {

    private Long product_id;

    private String product_name;

    private int product_num;

    private double product_price;

    private double total_price;

    private String product_pic_url;

}
