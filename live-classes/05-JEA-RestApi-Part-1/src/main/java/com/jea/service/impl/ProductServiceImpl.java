package com.jea.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jea.entities.Product;
import com.jea.repository.ProductRepository;
import com.jea.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product does not exist in DB."));
	}

	@Override
	public List<Product> getProductsList() {
		return productRepository.findAll();
	}

	@Override
	public Product updateProductInfo(Long productId, Product product) {
		Product savedProduct = getById(productId);
		savedProduct.setProductName(product.getProductName());
		savedProduct.setPrice(product.getPrice());
		return productRepository.save(savedProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = getById(productId);
		productRepository.delete(product);
	}
}