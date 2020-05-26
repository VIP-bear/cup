package com.bear.cup.controller;

import com.bear.cup.controller.param.OrderReq;
import com.bear.cup.domain.Order;
import com.bear.cup.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理
 */

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * 保存订单
     * @param orderReq
     * @return
     */
    @ResponseBody
    @PostMapping("/order/save")
    public String saveOrder(@RequestBody OrderReq orderReq) {
        log.info("order: {}", orderReq);
        return orderService.saveOrder(orderReq);
    }

    /**
     * 保存全部订单
     * @param orderReqList
     * @return
     */
    @ResponseBody
    @PostMapping("/order/saveAll")
    public String saveAllOrder(@RequestBody List<OrderReq> orderReqList) {
        log.info("orderList: {}", orderReqList);
        return orderService.saveAllOrder(orderReqList);
    }

    /**
     * 根据用户名查找订单
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/order/{username}")
    public List<Order> findOrderByUsername (@PathVariable("username") String username) {
        log.info("user:{}", username);
        return orderService.findOrderByUsername(username);
    }

}
