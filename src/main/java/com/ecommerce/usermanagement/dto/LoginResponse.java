package com.ecommerce.usermanagement.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
	
	private String jwtToken;
	private String userPhoneNumber;
	

}
