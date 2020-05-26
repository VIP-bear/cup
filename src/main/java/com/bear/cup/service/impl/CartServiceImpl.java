package com.bear.cup.service.impl;

import com.bear.cup.controller.param.CartReq;
import com.bear.cup.dao.entity.CartEntity;
import com.bear.cup.dao.repo.CartRepository;
import com.bear.cup.domain.Cart;
import com.bear.cup.service.ICartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;


    /**
     * 将商品加入购物车
     * @param cartReq
     * @return
     */
    @Override
    public String joinCart(CartReq cartReq) {
        CartEntity cartEntity = new CartEntity();
        BeanUtils.copyProperties(cartReq, cartEntity);
        cartEntity.setTotal_price(cartReq.getProduct_price() * cartReq.getProduct_num());
        CartEntity result = cartRepository.save(cartEntity);
        if (result == null) {
            return "添加失败，请重试!";
        }
        return "添加成功";
    }

    /**
     * 根据用户名删除购物车中的商品
     * @param username
     * @return
     */
    @Transactional
    @Override
    public String deleteCartByUsername(String username) {
        cartRepository.deleteByUsername(username);
        return "删除成功";
    }

    /**
     * 根据商品id从购物车中删除商品
     * @param productId
     * @return
     */
    @Transactional
    @Override
    public String deleteCartByProductId(Long productId) {
        cartRepository.deleteByProductId(productId);
        return "删除成功";
    }

    /**
     * 根据用户名和商品id从购物车中删除商品
     * @param username
     * @param productId
     */
    @Transactional
    @Override
    public String deleteCartByUsernameAndProductId(String username, Long productId) {
        cartRepository.deleteByUsernameAndProductId(username, productId);
        return "删除成功";
    }

    /**
     * 根据用户名查找购物车
     * @param username
     * @return
     */
    @Override
    public List<Cart> findCartByUsername(String username) {
        List<Cart> cartList = new ArrayList<>();
        List<CartEntity> cartEntityList = cartRepository.findAllByUsername(username);
        for (CartEntity cartEntity : cartEntityList) {
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartEntity, cart);
            cartList.add(cart);
        }
        return cartList;
    }

    /**
     * 根据用户名和id批量删除购物车中的商品
     * @param cartReqList
     * @param username
     * @return
     */
    @Transactional
    @Override
    public String deleteAllSelect(List<CartReq> cartReqList, String username) {
        for (CartReq cartReq : cartReqList) {
            cartRepository.deleteByUsernameAndProductId(username, cartReq.getProduct_id());
        }
        return "删除成功";
    }
}
