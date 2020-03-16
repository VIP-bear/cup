package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * DAO:Data Access Object
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * 使用本地sql查询
     * ?1:代表第一个参数
     * nativeQuery=true:开启本地查询
     * @param username
     * @return
     */

    @Query(value = "select * from t_user where username = ?1", nativeQuery = true)
    UserEntity findUserEntityByUsername(String username);
}
