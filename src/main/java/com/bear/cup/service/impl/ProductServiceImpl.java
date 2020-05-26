package com.bear.cup.service.impl;

import com.bear.cup.controller.param.ProductReq;
import com.bear.cup.dao.entity.ProductEntity;
import com.bear.cup.dao.repo.ProductRepository;
import com.bear.cup.domain.DetailProduct;
import com.bear.cup.domain.Product;
import com.bear.cup.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.criteria.Predicate;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 将产品信息保存到产品表中
     * @param productReq
     * @return
     */
    @Override
    public String saveProduct(ProductReq productReq) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productReq, productEntity);
        // 待审核状态
        productEntity.setProduct_check(0);
        // 产品发布时间
        productEntity.setProduct_publish_time(System.currentTimeMillis());
        ProductEntity result = productRepository.save(productEntity);
        if (result == null){
            return "发布失败";
        }
        return "发布成功，等待审核";
    }

    /**
     * 根据商品标签分页查找商品
     * @param page
     * @param size
     * @param tag
     * @return
     */
    @Override
    public Page<Product> findProductByTag(Integer page, Integer size, String tag) {
        // 按时间顺序排序
        PageRequest request = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "id"));
        // 构造自定义查询条件
        Specification<ProductEntity> queryCondition = (root, cq, cb) -> {
            // 多条件查询
            List<javax.persistence.criteria.Predicate> predicateList = new ArrayList<>();
            // 根据标签查询
            if (tag != null && !tag.equals("")) {
                predicateList.add(cb.equal(root.get("product_tag"), tag));
            }
            // 根据审核状态查询，1表示通过审核
            predicateList.add(cb.equal(root.get("product_check"), 1));
            // 转换成数组，然后组合查询条件
            javax.persistence.criteria.Predicate[] predicates = new Predicate[predicateList.size()];
            return cb.and(predicateList.toArray(predicates));
        };

        Page<ProductEntity> productEntities = productRepository.findAll(queryCondition, request);
        List<Product> products = productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            Date date = new Date(productEntity.getProduct_publish_time());
            product.setProduct_publish_time(date.toString());
            return product;
        }).collect(Collectors.toList());
        Page<Product> productPage = new PageImpl(products, request, productEntities.getTotalElements());
        return productPage;
    }

    /**
     * 根据商品名模糊搜索商品
     * @param productName
     * @return
     */
    @Override
    public List<Product> searchProduct(String productName) {
        List<ProductEntity> productEntities = productRepository.searchProductByName(productName);
        List<Product> products = productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            Date date = new Date(productEntity.getProduct_publish_time());
            product.setProduct_publish_time(date.toString());
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    /**
     * 根据商品id查找商品
     * @param id
     * @return
     */
    @Override
    public DetailProduct findProductById(Long id) {
        Optional<ProductEntity> byId = productRepository.findById(id);
        ProductEntity productEntity = byId.get();
        DetailProduct product = new DetailProduct();
        BeanUtils.copyProperties(productEntity, product);
        // 时间转换成字符串
        Date date = new Date(productEntity.getProduct_publish_time());
        product.setProduct_publish_time(date.toString());
        return product;
    }

    /**
     * 根据商品id查找商品数量
     * @param id
     * @return
     */
    @Override
    public int getProductNum(Long id) {
        int productNum = productRepository.getProductNumById(id);
        return productNum;
    }

    /**
     * 修改商品剩余数量
     * @param restNum
     */
    @Transactional
    @Override
    public void updateProductNum(Long productId, int restNum) {
        productRepository.updateProductNum(productId, restNum);
    }

    /**
     * 根据id删除商品
     * @param product_id
     */
    @Transactional
    @Override
    public void deleteProductById(Long product_id) {
        productRepository.deleteById(product_id);
    }
}
