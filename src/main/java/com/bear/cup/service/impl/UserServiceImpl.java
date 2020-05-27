package com.bear.cup.service.impl;

import com.bear.cup.controller.param.UserLoginReq;
import com.bear.cup.controller.param.UserRegisterReq;
import com.bear.cup.dao.entity.UserEntity;
import com.bear.cup.dao.repo.UserRepository;
import com.bear.cup.domain.Product;
import com.bear.cup.domain.User;
import com.bear.cup.enums.UserTypeEnum;
import com.bear.cup.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务类
 * 封装业务逻辑
 */
@Service
public class UserServiceImpl implements IUserService {

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

    /**
     * 分页查找用户
     * @return
     */
    @Override
    public Page<User> findAllUser(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<UserEntity> userEntityPage = userRepository.findAll(request);
        List<User> userList = userEntityPage.stream().map(userEntity -> {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }).collect(Collectors.toList());
        Page<User> userPage = new PageImpl(userList, request, userEntityPage.getTotalElements());
        return userPage;
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public User findUserById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        User user = new User();
        BeanUtils.copyProperties(byId.get(), user);
        return user;
    }

}
