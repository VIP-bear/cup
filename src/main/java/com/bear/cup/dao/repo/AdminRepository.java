package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 管理员数据访问
 */

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    /**
     * 根据用户名查询用户
     */
    @Query(value = "select * from t_admin where username = ?1", nativeQuery = true)
    AdminEntity findAdminEntityByUsername(String username);
}
