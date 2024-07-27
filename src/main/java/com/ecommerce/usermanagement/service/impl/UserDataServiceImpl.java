package com.ecommerce.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.Product;
import com.ecommerce.usermanagement.model.ProductImage;
import com.ecommerce.usermanagement.model.UserImage;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.ProductImageRepo;
import com.ecommerce.usermanagement.repo.ProductRepo;
import com.ecommerce.usermanagement.repo.UserImageRepo;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.service.UserDataService;

import jakarta.transaction.Transactional;

@Component
public class UserDataServiceImpl implements UserDataService {
	
	@Autowired
	private UserProfileRepo userProfileRepo;
	
	@Autowired
	private UserImageRepo userImageRepo;
	

	@Autowired
    private ProductRepo productRepo ;
	

	@Autowired
    private ProductImageRepo productImageRepo ;

	@Override
	@Transactional
	public UserProfile saveUser(UserProfile user, UserImage img) {

		if(user!=null ) {
			var userData=userProfileRepo.saveAndFlush(user);
			img.setUserId(userData.getId());
			userImageRepo.saveAndFlush(img);
			return userData;
			
		}
		return null;
	}
	
	@Override
	@Transactional
	public Product saveProduct(Product product, ProductImage img) {
		var prdDetails=productRepo.saveAndFlush(product);
		img.setPIdFk(prdDetails.getId());
		productImageRepo.saveAndFlush(img);
		
		return prdDetails;
	}

}
