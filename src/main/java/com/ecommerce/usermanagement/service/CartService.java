package com.ecommerce.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.Cart;

@Service
public interface CartService {
	
	public Cart saveCart(Integer productId, Integer userId);

	public List<Cart> getCartsByUser(Integer userId);

	Integer getCountCart(Integer userId);

	void updateQuantity(String sy, Integer cid);

}
