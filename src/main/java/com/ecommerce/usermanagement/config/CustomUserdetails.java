package com.ecommerce.usermanagement.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.usermanagement.model.UserProfile;
 
public class CustomUserdetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;


	private UserProfile user;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserdetails(UserProfile user){
		this.user=user;

		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(user.getUserRole().name());
		this.authorities= List.of(simpleGrantedAuthority);
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
				return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserPhoneNumber();
	}

}
