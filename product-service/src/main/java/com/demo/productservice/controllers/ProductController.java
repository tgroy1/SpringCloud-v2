package com.demo.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.productservice.dto.Coupon;
import com.demo.productservice.model.Product;
import com.demo.productservice.repo.ProductRepo;
import com.demo.productservice.restclients.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping(value = "/productapi")
@RefreshScope
public class ProductController {

	@Autowired
	private CouponClient couponClient;
	
	@Autowired
	private ProductRepo repo;
	
	@Value("${custom.property}")
	private String prop;

	@RequestMapping("/createProduct")
	@PostMapping
	@Retry(name = "create-product", fallbackMethod = "handleError")
	public Product createProduct(@RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);
	}
	
	@RequestMapping("/prop")
	@GetMapping
	public String getProp() {
		return this.prop;
	}
	
	public Product handleError(Product product, Exception ex) {
		System.out.println("Inside handleError method");
		return product;
	}

}
