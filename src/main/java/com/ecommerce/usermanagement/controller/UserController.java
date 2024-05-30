package com.ecommerce.usermanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usermanagement.dto.UserRegistrationResponse;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.service.UserRegService;
import com.ecommerce.usermanagement.service.impl.UserRegServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRegServiceImpl userRegService;

	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponse> userReg(@RequestBody UserProfile userProfile) throws Exception{
		Optional<UserProfile> userCreated = Optional.ofNullable(userRegService.usercreation(userProfile));
		if(userCreated.isEmpty()) {
			throw new Exception("Failed to create user");
		}
		UserRegistrationResponse userResponse =new UserRegistrationResponse();
		userResponse.setUserName(userCreated.get().getUserName());
		userResponse.setUserPhoneNumber(userCreated.get().getUserPhoneNumber());
		return new ResponseEntity<UserRegistrationResponse>(userResponse, HttpStatus.CREATED);
		}
		
	}
