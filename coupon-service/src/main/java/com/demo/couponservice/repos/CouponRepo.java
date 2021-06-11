package com.demo.couponservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.couponservice.model.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);
}
