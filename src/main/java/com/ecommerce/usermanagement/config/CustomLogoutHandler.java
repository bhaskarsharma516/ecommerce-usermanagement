package com.ecommerce.usermanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.repo.TokenDetailRepo;
import com.ecommerce.usermanagement.security.JwtHelper;
import com.ecommerce.usermanagement.service.RedisService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {


	@Autowired
	private TokenDetailRepo tokenDetailRepo;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private JwtHelper helper;
	
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		 String requestHeader = request.getHeader("Authorization");
	
	        if (requestHeader == null || !requestHeader.startsWith("Bearer")) 
	        	return;
	        
	         String token = requestHeader.substring(7);
	         log.info("logout token"+token);
	         
	         var res = tokenDetailRepo.findByJwtToken(token);
	         log.info("logout responseFromDb"+res);
	         
	         //setting token expired
	         if(res!=null) {
	        	 res.setValid(false);
		         log.info("logout set"+res);
		         tokenDetailRepo.save(res);

	         }
	         
	         String username=null;
	         
	         try {

                 username = this.helper.getUsernameFromToken(token);

             } catch (IllegalArgumentException e) {
                 log.info("Illegal Argument while fetching the username !!");
                 e.printStackTrace();
             } catch (ExpiredJwtException e) {
                 log.info("Given jwt token is expired !!");
                 e.printStackTrace();
             } catch (MalformedJwtException e) {
                 log.info("Some changed has done in token !! Invalid Token");
                 e.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();

             }
	         
	         //removing session from redis
	         log.info("usernmae "+username);
//	     
	         redisService.delete(username);
	         
	         
	    
	     
	       
	        
	         
	}

}
