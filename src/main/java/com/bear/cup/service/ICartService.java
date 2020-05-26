package com.bear.cup.service;

import com.bear.cup.controller.param.CartReq;
import com.bear.cup.domain.Cart;

import java.util.List;

public interface ICartService {

    String joinCart(CartReq cartReq);

    String deleteCartByUsername(String username);

    String deleteCartByProductId(Long productId);

    String deleteCartByUsernameAndProductId(String username, Long productId);

    List<Cart> findCartByUsername(String username);

    String deleteAllSelect(List<CartReq> cartReqList, String username);
}
