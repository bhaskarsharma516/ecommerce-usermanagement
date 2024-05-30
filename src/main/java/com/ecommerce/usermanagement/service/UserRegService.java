package com.ecommerce.usermanagement.service;

import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.model.UserProfile;

@Service
public interface UserRegService {
	
	UserProfile usercreation(UserProfile userData) throws Exception;

}
