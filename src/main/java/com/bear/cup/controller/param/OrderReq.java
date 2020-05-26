package com.bear.cup.controller.param;

import lombok.Data;

/**
 * 前端传过来的订单信息
 */

@Data
public class OrderReq {

    private String username;

    private Long product_id;

    private String product_name;

    private int product_num;

    private double product_price;

}
