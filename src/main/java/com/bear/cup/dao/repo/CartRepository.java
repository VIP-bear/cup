package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车库表相关的操作
 */

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Modifying
    @Query(value = "delete from t_cart where username=?1", nativeQuery = true)
    void deleteByUsername(String username);

    @Modifying
    @Query(value = "delete from t_cart where product_id=?1", nativeQuery = true)
    void deleteByProductId(Long productId);

    @Modifying
    @Query(value = "delete from t_cart where username=?1 and product_id=?2", nativeQuery = true)
    void deleteByUsernameAndProductId(String username, Long productId);

    @Query(value = "select * from t_cart where username=?1", nativeQuery = true)
    List<CartEntity> findAllByUsername(String username);
}
