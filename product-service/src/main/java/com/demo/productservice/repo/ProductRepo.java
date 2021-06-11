package com.demo.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
