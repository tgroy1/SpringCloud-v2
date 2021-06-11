package com.demo.productservice.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.productservice.dto.Coupon;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {

	@GetMapping("/couponapi/getCoupon")
	public Coupon getCoupon(@RequestParam String code);
}
