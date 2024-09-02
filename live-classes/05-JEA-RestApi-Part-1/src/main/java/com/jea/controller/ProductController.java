package com.jea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jea.entities.Product;
import com.jea.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		Product addedProduct = productService.addProduct(product);
		return addedProduct;
	}
	
	@GetMapping("/all-products")
	public List<Product> getAllProducts(){
		return productService.getProductsList();
	}
	
	@PutMapping("{productId}")
	public Product updateProductInfo(@PathVariable Long productId, @RequestBody Product product) {
		Product updatedProduct = productService.updateProductInfo(productId, product);
		return updatedProduct;
	}
	
	@DeleteMapping("/delete-product")
	public void deleteProduct(@RequestParam Long productId) {
		productService.deleteProduct(productId);
	}
	
	@GetMapping("{barCode}")
	public Product getProductByBarCode(@PathVariable String barCode){
		return productService.findProductByBarCode(barCode);
	}
}
