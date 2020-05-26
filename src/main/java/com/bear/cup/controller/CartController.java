package com.bear.cup.controller;

import com.bear.cup.controller.param.CartReq;
import com.bear.cup.domain.Cart;
import com.bear.cup.service.impl.CartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理
 */

@Controller
@Slf4j
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    /**
     * 商品加入购物车
     * @param cartReq
     * @return
     */
    @ResponseBody
    @PostMapping("/cart/join")
    public String joinCart(@RequestBody CartReq cartReq) {
        log.info("cartReq:{}", cartReq);
        return cartService.joinCart(cartReq);
    }

    /**
     * 根据用户名删除购物车中的商品
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/cart/{username}")
    public String deleteCartByUsername(@PathVariable("username")String username) {
        log.info("username: {}", username);
        return cartService.deleteCartByUsername(username);
    }

    /**
     * 根据商品id从购物车中删除商品
     * @param productId
     * @return
     */
    @ResponseBody
    @GetMapping("/cart/deleteByProductId/{productId}")
    public String deleteCartByProductId(@PathVariable("productId")Long productId) {
        log.info("productId: {}", productId);
        return cartService.deleteCartByProductId(productId);
    }

    /**
     * 根据用户名和商品id从购物车中删除商品
     * @param username
     * @param productId
     * @return
     */
    @ResponseBody
    @GetMapping("/cart/delete/{username}/{productId}")
    public String deleteCartByUsernameAndProductId(@PathVariable("username")String username,
                                                   @PathVariable("productId")Long productId) {
        log.info("username: {}, productId: {}", username, productId);
        return cartService.deleteCartByUsernameAndProductId(username, productId);
    }

    /**
     * 根据用户名和id批量删除购物车中的商品
     * @param cartReqList
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping("/cart/deleteAllSelect/{username}")
    public String deleteAllSelect(@RequestBody List<CartReq> cartReqList,
                                  @PathVariable("username")String username) {
        log.info("cartReqList: {}, username: {}", cartReqList, username);
        return cartService.deleteAllSelect(cartReqList, username);
    }

    /**
     * 根据用户名查找购物车
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/cart/findAll/{username}")
    public List<Cart> findCartByUsername(@PathVariable("username")String username) {
        log.info("username: {}", username);
        return cartService.findCartByUsername(username);
    }

}
