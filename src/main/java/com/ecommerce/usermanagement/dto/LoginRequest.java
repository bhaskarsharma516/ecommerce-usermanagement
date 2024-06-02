package com.ecommerce.usermanagement.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {
	private String userPhoneNumber;
	private String userPassword;
	

}
