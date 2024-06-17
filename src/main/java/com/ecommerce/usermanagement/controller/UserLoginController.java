package com.ecommerce.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.usermanagement.dto.LoginRequest;
import com.ecommerce.usermanagement.dto.LoginResponse;
import com.ecommerce.usermanagement.security.JwtHelper;
import com.ecommerce.usermanagement.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class UserLoginController {
	
	
		
	@Autowired
    private AuthenticationManager manager;
	
	@Autowired
    private JwtHelper helper;
	
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	


	 @PostMapping("/loginprocess")
	    public String login( LoginRequest request,RedirectAttributes redirect) {
		 
		Object redisRes= redisService.get(request.getUserPhoneNumber());
		log.info("redis response "+redisRes);
		if(redisRes!=null) {
			
		        return "index";
		}
		else {

		 String token = this.helper.generateToken( this.doAuthenticate(request.getUserPhoneNumber(),request.getUserPassword()));
		 log.info("token"+token);
		 redisService.set(request.getUserPhoneNumber(), token, 30L);
//
//	        LoginResponse response = LoginResponse.builder()
//	                .jwtToken(token)
//	                .build();
	        if(token==null) {
	        	redirect.addFlashAttribute("error","Login Failed");
	        	return "redirect:/auth/login";
	        }
	        return "index";
		}
	    }

	
	private Authentication doAuthenticate(String userId, String password) {

       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, password);
       try {
          return  manager.authenticate(authentication);


       } catch (BadCredentialsException e) {
           throw new BadCredentialsException(" Invalid Username or Password  !!");
       }

   }

   @ExceptionHandler(BadCredentialsException.class)
   public String exceptionHandler(RedirectAttributes redirect) {
       redirect.addFlashAttribute("error","Credentials Invalid !!");
       return "redirect:/auth/login";
   }
	
}
