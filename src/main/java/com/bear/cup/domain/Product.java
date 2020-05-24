package com.bear.cup.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 传给前端的商品数据
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private  Long id;

    private String product_name;

    private String product_pic_url;

    private double price;

    private String product_publish_time;

    private String product_publisher;

}
