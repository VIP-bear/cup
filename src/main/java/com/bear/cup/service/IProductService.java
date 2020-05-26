package com.bear.cup.service;

import com.bear.cup.controller.param.ProductReq;
import com.bear.cup.domain.DetailProduct;
import com.bear.cup.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {

    String saveProduct(ProductReq productReq);

    Page<Product> findProductByTag(Integer page, Integer size, String tag);

    List<Product> searchProduct(String productName);

    DetailProduct findProductById(Long id);

    int getProductNum(Long id);

    void updateProductNum(Long productId, int restNum);

    void deleteProductById(Long product_id);
}
