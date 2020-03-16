package com.bear.cup.service.impl;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;
import com.bear.cup.dao.entity.UserEntity;
import com.bear.cup.dao.repo.UserRepository;
import com.bear.cup.enums.UserTypeEnum;
import com.bear.cup.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 * 封装业务逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(UserLoginReq userLoginReq) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(userLoginReq.getUsername());
        if (userEntity == null){
            return UserTypeEnum.USER_NOT_EXIST.getMessage();
        }
        // 密码校验，先使用简单的字符串比较
        if (!userLoginReq.getPassword().equals(userEntity.getPassword())){
            return UserTypeEnum.PASSWORD_NOT_CORRECT.getMessage();
        }
        return UserTypeEnum.LOGIN_SUCCESS.getMessage();
    }

    @Override
    public String register(UserRegisterReq userRegisterReq) {
        if (!userRegisterReq.getPassword().equals(userRegisterReq.getRepassword())){
            // 两次密码不同
            return UserTypeEnum.INCONSISTENT_PASSWORD_TWICE.getMessage();
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRegisterReq, userEntity);
        if (userRepository.findUserEntityByUsername(userRegisterReq.getUsername()) != null){
            // 用户已存在
            return UserTypeEnum.USER_EXISTED.getMessage();
        }
        if (userRepository.save(userEntity) == null){
            // 注册失败
            return UserTypeEnum.LOGIN_FAILED.getMessage();
        }
        return UserTypeEnum.REGISTER_SUCCESS.getMessage();
    }
}
