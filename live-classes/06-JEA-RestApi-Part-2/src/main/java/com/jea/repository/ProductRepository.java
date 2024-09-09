package com.jea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.entities.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	//DSL [Domain Specific Language] Method
	Product findByBarCode(String barCode);
	
	/**
	 * If we don't use @Transactional in below method then we'll get this exception :  current thread - cannot reliably process 'remove' call 
	 */
	@Transactional
	void deleteByProductName(String productName);
}