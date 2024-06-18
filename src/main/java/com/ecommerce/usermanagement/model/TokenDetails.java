package com.ecommerce.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TokenDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tokenId;
	private String jwtToken;
	private String userPhoneNumber;
	private boolean valid; 

}
