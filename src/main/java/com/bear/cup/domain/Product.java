package com.bear.cup.domain;

import lombok.Data;

/**
 * 传给前端的商品数据
 */

@Data
public class Product {

    private  Long id;

    private String product_name;

    private String product_pic_url;

    private double price;

    private String product_publish_time;

    private String product_publisher;

}
