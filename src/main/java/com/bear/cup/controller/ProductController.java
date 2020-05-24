package com.bear.cup.controller;

import com.bear.cup.controller.param.ProductReq;
import com.bear.cup.domain.DetailProduct;
import com.bear.cup.domain.Product;
import com.bear.cup.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品管理
 */

@Controller
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 产品发布
     * @param productReq
     * @return
     */
    @ResponseBody
    @PostMapping("/publish")
    public String publish(@RequestBody ProductReq productReq){
        log.info("Product Message:{}", productReq);
        String publishMessage = productService.saveProduct(productReq);
        return publishMessage;
    }

    /**
     * 根据商品标签分页查找商品
     * @param page
     * @param size
     * @return
     */
    @ResponseBody
    @PostMapping("/product/findProductByTag/{page}/{size}")
    public Page<Product> findProductByTag(@PathVariable("page")Integer page,
                                          @PathVariable("size")Integer size,
                                          @RequestParam(name = "product_tag")String product_tag) {
        log.info("page:{}, size:{}", page, size, product_tag);
        return productService.findProductByTag(page, size, product_tag);
    }

    /**
     * 根据商品名模糊搜索商品
     * @param input
     * @return
     */
    @ResponseBody
    @PostMapping("/product/search")
    public List<Product> searchProduct(@RequestParam(name = "input")String input) {
        log.info("input: {}", input);
        return productService.searchProduct(input);
    }

    /**
     * 根据id查找商品
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/product/{id}")
    public DetailProduct findProductById(@PathVariable("id")Long id) {
        log.info("id: {}", id);
        return productService.findProductById(id);
    }

}