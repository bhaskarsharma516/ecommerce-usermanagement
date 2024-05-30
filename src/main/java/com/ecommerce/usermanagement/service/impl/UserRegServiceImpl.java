package com.ecommerce.usermanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.service.UserRegService;

@Component
public class UserRegServiceImpl implements UserRegService {
	
	@Autowired
	private UserProfileRepo userProfileRepo;

	@Override
	public UserProfile usercreation(UserProfile userData) throws Exception {
		Optional<UserProfile> userNumberExist=userProfileRepo.findByNumber(userData.getUserPhoneNumber());
		if(!userNumberExist.isEmpty())
			throw new Exception("Phone number is already present");
		return userProfileRepo.save(userData);
	}

}
