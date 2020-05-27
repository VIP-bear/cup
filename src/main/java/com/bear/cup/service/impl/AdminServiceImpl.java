package com.bear.cup.service.impl;

import com.bear.cup.controller.param.AdminLoginReq;
import com.bear.cup.dao.entity.AdminEntity;
import com.bear.cup.dao.repo.AdminRepository;
import com.bear.cup.domain.Product;
import com.bear.cup.domain.User;
import com.bear.cup.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 管理员服务类
 */

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public String login(AdminLoginReq adminLoginReq) {
        AdminEntity adminEntity = adminRepository.findAdminEntityByUsername(adminLoginReq.getUsername());
        if (adminEntity == null) {
            return "管理员不存在";
        }
        if (!adminLoginReq.getPassword().equals(adminEntity.getPassword())) {
            return "用户名或密码错误";
        }
        return "登录成功";
    }

    /**
     * 分页查询所有用户
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<User> findAllUser(int page, int size) {
        return userService.findAllUser(page, size);
    }

    /**
     * 添加管理员
     * @param id
     * @return
     */
    @Override
    public String addAdmin(Long id) {
        // 获取用户信息
        User user = userService.findUserById(id);
        // 在管理员表中查找用户
        AdminEntity admin = findByUsername(user.getUsername());
        if (admin == null) {
            admin = new AdminEntity();
            admin.setUsername(user.getUsername());
            admin.setPassword(user.getPassword());
            adminRepository.save(admin);
            return "设置成功";
        } else {
            return "该用户已经是管理员啦!";
        }
    }

    /**
     * 根据用户名查找管理员
     * @param username
     * @return
     */
    @Override
    public AdminEntity findByUsername(String username) {
        AdminEntity admin = adminRepository.findAdminEntityByUsername(username);
        return admin;
    }

    /**
     * 审核通过，发布商品
     * @param productId
     * @return
     */
    @Transactional
    @Override
    public String publishProduct(Long productId) {
        String result = productService.updateProductCheck(productId);
        return result;
    }

    /**
     * 根据商品id删除商品
     * @param productId
     * @return
     */
    @Transactional
    @Override
    public String deleteProduct(Long productId) {
        productService.deleteProductById(productId);
        return "删除成功";
    }

    /**
     * 分页获取全部已经发布的商品
     * @return
     * @param page
     * @param size
     */
    @Override
    public Page<Product> findProduct(int page, int size) {
        return productService.findProductByTag(page, size, null);
    }
}
