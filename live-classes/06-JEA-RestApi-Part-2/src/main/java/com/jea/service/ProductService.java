package com.jea.service;

import java.util.List;
import com.jea.entities.Product;

public interface ProductService {
	Product addProduct(Product product);
	Product getById(Long id);
	List<Product> getProductsList();
	Product updateProductInfo(Long productId, Product product);
	void deleteProduct(Long productId);
	List<Product> saveAllProducts(List<Product> products);
	//Implementing by DSL queries
	
	Product findProductByBarCode(String barCode);
	void deleteProductByName(String productName);
	
	
}