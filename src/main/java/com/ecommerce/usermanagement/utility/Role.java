package com.ecommerce.usermanagement.utility;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	USER,ADMIN,SELLER;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}

}
