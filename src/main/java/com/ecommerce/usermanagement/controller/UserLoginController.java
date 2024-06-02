package com.ecommerce.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usermanagement.dto.LoginRequest;
import com.ecommerce.usermanagement.dto.LoginResponse;
import com.ecommerce.usermanagement.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class UserLoginController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
    private AuthenticationManager manager;
	
	@Autowired
    private JwtHelper helper;
    


	 @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

	        this.doAuthenticate(request.getUserPhoneNumber(),request.getUserPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserPhoneNumber());
	        String token = this.helper.generateToken(userDetails);

	        LoginResponse response = LoginResponse.builder()
	                .jwtToken(token)
	                .userPhoneNumber(userDetails.getUsername())
	                .build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	
	private void doAuthenticate(String email, String password) {

       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
       try {
           manager.authenticate(authentication);


       } catch (BadCredentialsException e) {
           throw new BadCredentialsException(" Invalid Username or Password  !!");
       }

   }

   @ExceptionHandler(BadCredentialsException.class)
   public String exceptionHandler() {
       return "Credentials Invalid !!";
   }
	
}
