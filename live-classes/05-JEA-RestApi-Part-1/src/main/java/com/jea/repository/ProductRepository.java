package com.jea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}