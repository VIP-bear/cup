package com.bear.cup.service;

import com.bear.cup.controller.param.AdminLoginReq;
import com.bear.cup.dao.entity.AdminEntity;
import com.bear.cup.domain.Product;
import com.bear.cup.domain.User;
import org.springframework.data.domain.Page;

public interface IAdminService {

    /**
     * 管理员登陆
     * @param adminLoginReq
     * @return
     */
    String login(AdminLoginReq adminLoginReq);

    Page<User> findAllUser(int page, int size);

    String addAdmin(Long id);

    AdminEntity findByUsername(String username);

    String publishProduct(Long productId);

    String deleteProduct(Long productId);

    Page<Product> findProduct(int page, int size);

}
