package com.bear.cup.controller;

import com.bear.cup.controller.param.AdminLoginReq;
import com.bear.cup.domain.Product;
import com.bear.cup.domain.User;
import com.bear.cup.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员
 */

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 管理员登陆
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/login")
    public String login(@RequestBody AdminLoginReq adminLoginReq) {
        log.info("AdminLoginReq : {}", adminLoginReq);
        String loginMessage = adminService.login(adminLoginReq);
        log.info("Login User:{}", loginMessage);
        return loginMessage;
    }

    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/findAllUser/{page}/{size}")
    public Page<User> findAllUser(@PathVariable("page")int page,
                                  @PathVariable("size")int size) {
        log.info("page: {}, size: {}", page, size);
        return adminService.findAllUser(page, size);
    }

    /**
     * 根据用户id添加管理员
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/add/{id}")
    public String addAdmin(@PathVariable("id")Long id) {
        log.info("id: {}", id);
        return adminService.addAdmin(id);
    }

    /**
     * 管理员审核通过，发布商品
     * @param productId
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/publish/{product_id}")
    public String publishProduct(@PathVariable("product_id")Long productId) {
        log.info("productId: {}", productId);
        return adminService.publishProduct(productId);
    }

    /**
     * 管理员删除商品
     * @param productId
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/deleteProduct/{product_id}")
    public String deleteProduct(@PathVariable("product_id")Long productId) {
        log.info("productId: {}", productId);
        return adminService.deleteProduct(productId);
    }

    /**
     * 分页获取全部已经发布的商品
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/findProduct/{page}/{size}")
    public Page<Product> findProduct(@PathVariable("page")int page,
                                     @PathVariable("size")int size) {
        return adminService.findProduct(page, size);
    }
}
