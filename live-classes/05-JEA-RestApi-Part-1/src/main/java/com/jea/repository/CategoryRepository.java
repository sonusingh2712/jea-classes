package com.jea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
