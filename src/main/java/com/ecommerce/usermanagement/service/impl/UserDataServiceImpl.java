package com.ecommerce.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.UserImage;
import com.ecommerce.usermanagement.model.UserProfile;
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

	@Override
	@Transactional
	public UserProfile saveUser(UserProfile user, UserImage img) {

		if(user!=null ) {
			var userData=userProfileRepo.saveAndFlush(user);
			img.setUserId(userData.getUserId());
			userImageRepo.saveAndFlush(img);
			return userData;
			
		}
		return null;
	}

}
