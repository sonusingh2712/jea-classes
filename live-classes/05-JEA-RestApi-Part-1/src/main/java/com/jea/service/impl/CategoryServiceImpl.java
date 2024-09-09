package com.jea.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jea.entities.Category;
import com.jea.repository.CategoryRepository;
import com.jea.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category does not exist in DB."));
	}

	@Override
	public List<Category> getCategoriesList() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategoryInfo(Long categoryid, Category category) {
		Category savedCategory = getById(categoryid);
		savedCategory.setCategoryName(category.getCategoryName());
		return categoryRepository.save(savedCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = getById(id);
		categoryRepository.delete(category);
	}
	
	@Override
	public Category getCategoryByName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}
}