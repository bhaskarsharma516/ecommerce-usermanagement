package com.ecommerce.usermanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usermanagement.dto.UserRegistrationResponse;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.service.impl.UserRegServiceImpl;

@RestController
@RequestMapping("/user")
public class UserSignupController{
	
	@Autowired
	private UserRegServiceImpl userRegService;
	

	@Autowired
    private PasswordEncoder passwordEncoder ;
	


	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponse> userReg(@RequestBody UserProfile userProfile) throws Exception{
		userProfile.setUserPassword(passwordEncoder.encode(userProfile.getUserPassword()));
		System.out.println(userProfile);
		Optional<UserProfile> userCreated = Optional.ofNullable(userRegService.usercreation(userProfile));
		if(userCreated.isEmpty()) {
			throw new Exception("Failed to create user");
		}
		UserRegistrationResponse userResponse = UserRegistrationResponse.builder()
				.name(userCreated.get().getName())
				.userPhoneNumber(userCreated.get().getUserPhoneNumber())
				.build();
		
		return new ResponseEntity<UserRegistrationResponse>(userResponse, HttpStatus.CREATED);
		}
	
	
		
	}
