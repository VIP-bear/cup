package com.bear.cup.service.impl;

import com.bear.cup.controller.param.OrderReq;
import com.bear.cup.dao.entity.OrderEntity;
import com.bear.cup.dao.repo.OrderRespository;
import com.bear.cup.domain.Order;
import com.bear.cup.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CartServiceImpl cartService;

    /**
     * 保存订单
     * @param orderReq
     * @return
     */
    @Transactional
    @Override
    public String saveOrder(OrderReq orderReq) {

        String message = "购买失败，请重试";

        // 查询商品剩余数量
        int restNum = productService.getProductNum(orderReq.getProduct_id());
        restNum -= orderReq.getProduct_num();

        if (restNum >= 0) {
            // 购买商品
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderReq, orderEntity);
            // 交易时间
            orderEntity.setOrder_time(System.currentTimeMillis());
            // 商品总价
            orderEntity.setTotal_price(orderReq.getProduct_price() * orderReq.getProduct_num());
            OrderEntity result = orderRespository.save(orderEntity);

            if (result != null) {
                if (restNum == 0) {
                    // 删除商品
                    productService.deleteProductById(orderReq.getProduct_id());
                } else {
                    // 修改商品剩余数量
                    productService.updateProductNum(orderReq.getProduct_id(), restNum);
                }
                message = "购买成功";
            }
        }
        return message;
    }

    /**
     * 根据用户名查找订单
     * @param username
     * @return
     */
    @Override
    public List<Order> findOrderByUsername(String username) {
        List<OrderEntity> orderEntities = orderRespository.findByUsername(username);
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            // 时间转换成字符串
            Date date = new Date(orderEntity.getOrder_time());
            order.setOrder_time(date.toString());
            orderList.add(order);
        }
        return orderList;
    }

    /**
     * 保存全部订单
     * @param orderReqList
     * @param username
     * @return
     */
    @Transactional
    @Override
    public String saveAllOrder(List<OrderReq> orderReqList, String username) {

        String message = "购买失败，请重试";

        // 封装OrderEntity
        List<OrderEntity> orderEntityList = new ArrayList<>();
        List<Integer> restNums = new ArrayList<>();
        for (OrderReq orderReq : orderReqList) {
            orderReq.setUsername(username);
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderReq, orderEntity);
            orderEntity.setOrder_time(System.currentTimeMillis());
            orderEntity.setProduct_price(orderReq.getProduct_price() * orderReq.getProduct_num());
            int restNum = productService.getProductNum(orderReq.getProduct_id());
            restNum -= orderReq.getProduct_num();
            restNums.add(restNum);
            if (restNum >= 0) {
                orderEntityList.add(orderEntity);
            }
        }

        if (restNums.size() == orderEntityList.size()) {
            // 保存订单
            List<OrderEntity> result = orderRespository.saveAll(orderEntityList);

            for (int i = 0; i < restNums.size(); i++) {
                if (restNums.get(i) == 0) {
                    // 删除商品
                    productService.deleteProductById(orderReqList.get(i).getProduct_id());
                } else {
                    // 修改商品剩余数量
                    productService.updateProductNum(orderReqList.get(i).getProduct_id(), restNums.get(i));
                }
                // 删除购物车中已经购买的商品
                cartService.deleteCartByUsernameAndProductId(orderReqList.get(i).getUsername(),
                        orderReqList.get(i).getProduct_id());
            }
            message = "购买成功";
        }
        return message;
    }
}
