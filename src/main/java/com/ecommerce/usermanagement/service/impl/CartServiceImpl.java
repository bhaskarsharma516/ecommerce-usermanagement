package com.ecommerce.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ecommerce.usermanagement.model.Cart;
import com.ecommerce.usermanagement.model.Product;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.CartRepo;
import com.ecommerce.usermanagement.repo.ProductRepo;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.service.CartService;

@Component
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepository;

	@Autowired
	private UserProfileRepo userRepository;

	@Autowired
	private ProductRepo productRepository;


	@Override
	public Cart saveCart(Integer productId, Integer userId) {
		// TODO Auto-generated method stub
		UserProfile userDtls = userRepository.findById(userId).get();
		Product product = productRepository.findById(productId).get();

		Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);

		Cart cart = null;

		if (ObjectUtils.isEmpty(cartStatus)) {
			cart = new Cart();
			cart.setProduct(product);
			cart.setUser(userDtls);
			cart.setQuantity(1);
			cart.setTotalPrice(1 * product.getDiscountPrice());
		} else {
			cart = cartStatus;
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
		}
		Cart saveCart = cartRepository.save(cart);

		return saveCart;
	}

	

	@Override
	public List<Cart> getCartsByUser(Integer userId) {
		List<Cart> carts = cartRepository.findByUserId(userId);

		Double totalOrderPrice = 0.0;
		List<Cart> updateCarts = new ArrayList<Cart>();
		for (Cart c : carts) {
			Double totalPrice = (c.getProduct().getDiscountPrice() * c.getQuantity());
			c.setTotalPrice(totalPrice);
			totalOrderPrice = totalOrderPrice + totalPrice;
			c.setTotalOrderPrice(totalOrderPrice);
			updateCarts.add(c);
		}

		return updateCarts;
	}
	
	@Override
	public Integer getCountCart(Integer userId) {
		Integer countByUserId = cartRepository.countByUserId(userId);
		return countByUserId;
	}
	
	@Override
	public void updateQuantity(String sy, Integer cid) {

		Cart cart = cartRepository.findByUserId(cid).get(0);
		int updateQuantity;

		if (sy.equalsIgnoreCase("de")) {
			updateQuantity = cart.getQuantity() - 1;

			if (updateQuantity <= 0) {
				cartRepository.delete(cart);
			} else {
				cart.setQuantity(updateQuantity);
				cartRepository.save(cart);
			}

		} else {
			updateQuantity = cart.getQuantity() + 1;
			cart.setQuantity(updateQuantity);
			cartRepository.save(cart);
		}

	}
	
	
	
}



