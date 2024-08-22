package com.jea.service;

import java.util.List;
import com.jea.entities.Category;

public interface CategoryService {
	Category addCategory(Category category);
	Category getById(Long id);
	List<Category> getCategoriesList();
	Category updateCategoryInfo(Long categoryid, Category category);
	void deleteCategory(Long id);
}