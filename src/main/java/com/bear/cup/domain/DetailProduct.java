package com.bear.cup.domain;

import lombok.Data;

/**
 * 传给前端的详细商品信息
 */

@Data
public class DetailProduct {

    private Long id;

    private String product_name;

    private String product_pic_url;

    private int product_num;

    private double price;

    private String product_publish_time;

    private String product_publisher;

    private String product_content;

}
