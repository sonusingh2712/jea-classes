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
import org.springframework.web.bind.annotation.RestController;

import com.jea.entities.Category;
import com.jea.service.CategoryService;

/**
 * As per recommendation,
 * Trying to achieve one-url approach for CRUD operation
 */
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.addCategory(category);
		return savedCategory;
	}
	
	@GetMapping
	public List<Category> getAllCategories(){
		return categoryService.getCategoriesList();
	}
	
	@PutMapping("{categoryId}")
	public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		Category updatedCategoryInfo = categoryService.updateCategoryInfo(categoryId, category);
		return updatedCategoryInfo;
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable (value = "categoryId") Long id) {
		categoryService.deleteCategory(id);
	}
	
	@GetMapping("{categoryName}")
	public Category getCategory(@PathVariable String categoryName) {
		return categoryService.getCategoryByName(categoryName);
	}
}