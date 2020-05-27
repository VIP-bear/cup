package com.bear.cup.dao.repo;

import com.bear.cup.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在商品库表中进行相关操作
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
        JpaSpecificationExecutor<ProductEntity> {

    @Query(value = "select * from t_product where product_check=1 and " +
            "product_name like concat('%',:productName,'%')", nativeQuery = true)
    List<ProductEntity> searchProductByName(String productName);

    @Query(value = "select product_num from t_product where id=?1", nativeQuery = true)
    int getProductNumById(Long id);

    @Modifying
    @Query(value = "update t_product set product_num = ?2 where id=?1", nativeQuery = true)
    void updateProductNum(Long productId, int restNum);

    @Modifying
    @Query(value = "update t_product set product_check = 1 where id=?1", nativeQuery = true)
    void updateProductCheck(Long productId);
}
