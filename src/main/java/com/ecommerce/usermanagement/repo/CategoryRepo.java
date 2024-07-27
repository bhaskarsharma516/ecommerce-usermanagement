package com.ecommerce.usermanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	public Boolean existsByName(String name);

	public List<Category> findByIsActiveTrue();

}
