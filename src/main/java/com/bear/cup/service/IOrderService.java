package com.bear.cup.service;

import com.bear.cup.controller.param.OrderReq;
import com.bear.cup.domain.Order;

import java.util.List;

public interface IOrderService {

    String saveOrder(OrderReq orderReq);

    List<Order> findOrderByUsername(String username);

    String saveAllOrder(List<OrderReq> orderReqList, String username);
}
