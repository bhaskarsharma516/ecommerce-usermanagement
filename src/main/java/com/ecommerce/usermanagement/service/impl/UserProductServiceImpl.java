package com.ecommerce.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.Product;
import com.ecommerce.usermanagement.model.ProductImage;
import com.ecommerce.usermanagement.service.UserProductService;

@Component
public class UserProductServiceImpl implements UserProductService {
	
	@Autowired
	private UserDataServiceImpl serviceImpl;

	@Override
	public Product saveProduct(Product product, ProductImage img) {
		// TODO Auto-generated method stub
		return serviceImpl.saveProduct(product, img);
	}

}
