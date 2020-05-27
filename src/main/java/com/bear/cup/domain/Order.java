package com.bear.cup.domain;

import lombok.Data;

/**
 * 传给前端的订单信息
 */

@Data
public class Order {

    private Long order_id;

    private String product_name;

    private String order_time;

    private int product_num;

    private double product_price;

    private double total_price;

}
