package com.bear.cup;

import com.bear.cup.dao.repo.ProductRepository;
import com.bear.cup.domain.DetailProduct;
import com.bear.cup.domain.Product;
import com.bear.cup.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CupApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductServiceImpl productService;


	@Test
	void contextLoads() {
		System.out.println(System.currentTimeMillis());
	}

	/**
	 * 测试搜索功能
	 */
	@Test
	void testSearch() {
		List<Product> products = productService.searchProduct("test");
		for (Product product : products) {
			System.out.println(product);
		}
	}

	/**
	 * 测试根据id查找商品
	 */
	@Test
	void testFindProductById() {
		DetailProduct product = productService.findProductById(4L);
		System.out.println(product);

	}

}
