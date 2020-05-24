package com.bear.cup.controller;

import com.bear.cup.controller.param.AdminLoginReq;
import com.bear.cup.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String login(AdminLoginReq adminLoginReq) {
        log.info("AdminLoginReq : {}", adminLoginReq);
        String loginMessage = adminService.login(adminLoginReq);
        log.info("Login User:{}", loginMessage);
        return loginMessage;
    }

}
