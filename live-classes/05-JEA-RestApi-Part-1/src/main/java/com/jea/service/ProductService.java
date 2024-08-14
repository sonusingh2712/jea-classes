package com.jea.service;

import java.util.List;
import com.jea.entities.Product;

public interface ProductService {
	Product addProduct(Product product);
	Product getById(Long id);
	List<Product> getProductsList();
	Product updateProductInfo(Product product);
	void deleteProduct(Long productId);
}