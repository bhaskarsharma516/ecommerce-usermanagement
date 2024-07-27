package com.ecommerce.usermanagement.service;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.Product;
import com.ecommerce.usermanagement.model.ProductImage;

@Service
public interface UserProductService {
	
	Product saveProduct(Product product,ProductImage img);

}
