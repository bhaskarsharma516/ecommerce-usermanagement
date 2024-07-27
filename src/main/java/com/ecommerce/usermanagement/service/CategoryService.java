package com.ecommerce.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.Category;

@Service
public interface CategoryService {

	public Category saveCategory(Category category);

	public Boolean existCategory(String name);

	public List<Category> getAllCategory();

	public Boolean deleteCategory(Integer id);

	public Category getCategoryById(Integer id);

	public List<Category> getAllActiveCategory();
}
