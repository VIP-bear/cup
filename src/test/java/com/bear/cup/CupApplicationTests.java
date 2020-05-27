package com.bear.cup;

import com.bear.cup.controller.param.CartReq;
import com.bear.cup.controller.param.OrderReq;
import com.bear.cup.dao.entity.OrderEntity;
import com.bear.cup.dao.repo.CartRepository;
import com.bear.cup.dao.repo.OrderRespository;
import com.bear.cup.dao.repo.ProductRepository;
import com.bear.cup.domain.DetailProduct;
import com.bear.cup.domain.Product;
import com.bear.cup.service.impl.CartServiceImpl;
import com.bear.cup.service.impl.OrderServiceImpl;
import com.bear.cup.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CupApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductServiceImpl productService;

	@Autowired
	OrderRespository orderRespository;

	@Autowired
	OrderServiceImpl orderService;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartServiceImpl cartService;


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

	/**
	 * 测试保存订单
	 */
	@Test
	void testSaveOrder() {
		OrderReq orderReq = new OrderReq();
		orderReq.setProduct_id(8L);
		orderReq.setProduct_name("贷记卡昆仑山搭街坊卡拉");
		orderReq.setProduct_num(2);
		orderReq.setProduct_price(11);
		orderReq.setUsername("xx");
		String s = orderService.saveOrder(orderReq);
		System.out.println(s);
	}

	/**
	 * 测试保存全部订单
	 */
	@Test
	void testSaveAllOrder() {
		List<OrderReq> orderReqList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			OrderReq orderReq = new OrderReq();
			orderReq.setProduct_id(5L+i);
			orderReq.setProduct_name("test"+i);
			orderReq.setProduct_num(2+i);
			orderReq.setProduct_price(1.1 + i);
			orderReq.setUsername("bear");
			orderReqList.add(orderReq);
		}

		String s = orderService.saveAllOrder(orderReqList, "bear");
		System.out.println(s);
	}

	/**
	 * 测试根据用户名查找订单
	 */
	@Test
	public void findOrderByUsername () {
		List<OrderEntity> orderByBear = orderRespository.findByUsername("bear");
		List<OrderEntity> orderByXx = orderRespository.findByUsername("xx");
		System.out.println(orderByBear);
		System.out.println(orderByXx);
	}

	/**
	 * 测试商品加入购物车
	 */
	@Test
	public void joinCart() {

		CartReq cartReq = new CartReq();
		cartReq.setProduct_id(14L);
		cartReq.setProduct_name("小点球");
		cartReq.setProduct_num(3);
		cartReq.setProduct_price(11.30);
		cartReq.setUsername("bear");

		String s = cartService.joinCart(cartReq);
		System.out.println(s);
	}

	/**
	 * 测试根据用户名删除购物车中的商品
	 */
	@Test
	public void deleteCartByUsername() {
		String t = cartService.deleteCartByUsername("t");
		System.out.println(t);
	}

	/**
	 * 测试根据商品id从购物车中删除商品
	 */
	@Test
	public void deleteCartByProductId() {
		String s = cartService.deleteCartByProductId(14L);
		System.out.println(s);
	}
}
