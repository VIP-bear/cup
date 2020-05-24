package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在产品库表中进行相关操作
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {

    @Query(value = "select * from t_product where product_check=1 and " +
            "product_name like concat('%',:productName,'%')", nativeQuery = true)
    List<ProductEntity> searchProductByName(String productName);
}
