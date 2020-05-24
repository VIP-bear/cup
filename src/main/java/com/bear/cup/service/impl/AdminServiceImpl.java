package com.bear.cup.service.impl;

import com.bear.cup.controller.param.AdminLoginReq;
import com.bear.cup.dao.entity.AdminEntity;
import com.bear.cup.dao.repo.AdminRepository;
import com.bear.cup.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 管理员服务类
 */

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

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
}
