package com.ecommerce.usermanagement.service;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.Product;
import com.ecommerce.usermanagement.model.ProductImage;
import com.ecommerce.usermanagement.model.UserImage;
import com.ecommerce.usermanagement.model.UserProfile;

@Service
public interface UserDataService {
	
	public UserProfile saveUser(UserProfile user,UserImage img) ;
	public Product saveProduct(Product product, ProductImage img);

}
