package com.bear.cup.controller.param;

import lombok.Data;

/**
 * 前端传过来的商品数据
 */

@Data
public class ProductReq {

    private String product_name;

    private String product_pic_url;

    private double price;

    private int product_num;

    private String product_tag;

    private String product_content;

    private String product_publisher;
}
