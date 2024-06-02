package com.ecommerce.usermanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {

	private String name;
	private String userPhoneNumber;
	
	
}
