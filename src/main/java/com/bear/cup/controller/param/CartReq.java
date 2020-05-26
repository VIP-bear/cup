package com.bear.cup.controller.param;

import lombok.Data;

/**
 * 前端传过来的购物车信息
 */

@Data
public class CartReq {

    private Long product_id;

    private String product_name;

    private String product_pic_url;

    private int product_num;

    private double product_price;

    private String username;

}
