package com.ecommerce.usermanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	public Cart findByProductIdAndUserId(Integer productId, Integer userId);

	public Integer countByUserId(Integer userId);

	public List<Cart> findByUserId(Integer userId);


}
