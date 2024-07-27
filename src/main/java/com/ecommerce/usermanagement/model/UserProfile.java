package com.ecommerce.usermanagement.model;

import java.util.Date;

import com.ecommerce.usermanagement.utility.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String userEmail;
	private String userPhoneNumber;
	private String userPassword;
	private String userPic;
	private String userAddress;
	private String city;
	private String state;
	private String pincode;
	@Enumerated(EnumType.STRING)
	private Role userRole;
	private Boolean isEnable;

	private Boolean accountNonLocked;

	private Integer failedAttempt;

	private Date lockTime;
	
	private String resetToken;
//	Collection<? extends GrantedAuthority> authorities;
//	private Collection<GrantedAuthority> authorities;
//	@Enumerated(EnumType.STRING)
//	private Role userRole;
//	
//	public UserProfile(UserProfile user) {
//		this.userPhoneNumber=user.getUserPhoneNumber();
//		this.userPassword=user.getUserPassword();
//		this.authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
//	}
	
	

}
