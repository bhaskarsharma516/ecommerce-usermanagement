package com.ecommerce.usermanagement.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserLogin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger userLoginPk;
	private BigInteger userProfileFk;
	private String jwtToken;
	private String userPhoneNumber;
	private LocalDateTime expiryTime;
}