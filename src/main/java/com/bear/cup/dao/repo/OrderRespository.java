package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在订单库表中进行相关操作
 */

@Repository
public interface OrderRespository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select * from t_order where username=?1 order by order_time desc", nativeQuery = true)
    List<OrderEntity> findByUsername(String username);
}
