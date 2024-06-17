package com.ecommerce.usermanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.UserImage;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.service.UserDataService;
import com.ecommerce.usermanagement.service.UserRegService;

@Component
public class UserRegServiceImpl implements UserRegService {
	
	@Autowired
	private UserProfileRepo userProfileRepo;
	

	@Autowired
    private UserDataService userDataService ;

	@Override
	public UserProfile usercreation(UserProfile userData,UserImage img) throws Exception {
		Optional<UserProfile> userNumberExist=Optional.ofNullable(userProfileRepo.findByNumber(userData.getUserPhoneNumber()));
		if(!userNumberExist.isEmpty())
			return null;
//			throw new Exception("Phone number is already present");
		return userDataService.saveUser(userData,img);
	}

}
