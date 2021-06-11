package com.demo.couponservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.couponservice.model.Coupon;
import com.demo.couponservice.repos.CouponRepo;

@RestController
@RequestMapping(value = "/couponapi")
public class CouponController {

	@Autowired
	Environment environment;
	
	@Autowired
	private CouponRepo repo;

	@PostMapping("/createCoupon")
	public Coupon createCoupon(@RequestBody Coupon coupon) {
		return repo.save(coupon);
	}

	@GetMapping(value = "/getCoupon")
	public Coupon getCoupon(@RequestParam String code) {
		System.out.println("Port = " + environment.getProperty("local.server.port"));
		return repo.findByCode(code);
	}
}
