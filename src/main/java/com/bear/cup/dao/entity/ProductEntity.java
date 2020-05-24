package com.bear.cup.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 产品库表
 */
@Data
@Entity
@Table(name = "t_product")
public class ProductEntity {
    /**
     * 产品id,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @Column
    private String product_name;

    /**
     * 产品图片地址
     */
    @Column
    private String product_pic_url;

    /**
     * 产品价格
     */
    @Column
    private double price;

    /**
     * 产品描述
     */
    @Column
    private String product_content;

    /**
     * 产品库存量
     */
    @Column
    private int product_num;

    /**
     * 产品标签
     */
    @Column
    private String product_tag;

    /**
     * 产品发布商
     */
    @Column
    private String product_publisher;

    /**
     * 产品发布时间
     */
    @Column
    private Long product_publish_time;

    /**
     * 产品审核状态
     * 0:审核中,1:发布成功,2:发布失败
     */
    @Column
    private int product_check;
}
