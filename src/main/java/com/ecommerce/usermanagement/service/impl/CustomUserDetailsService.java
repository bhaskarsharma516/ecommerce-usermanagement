package com.ecommerce.usermanagement.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.usermanagement.config.CustomUserdetails;
import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.utility.Role;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserProfileRepo userProfileRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	     UserProfile user = userProfileRepo.findByNumber(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return new CustomUserdetails(user);
	        
	}
	
	

}
