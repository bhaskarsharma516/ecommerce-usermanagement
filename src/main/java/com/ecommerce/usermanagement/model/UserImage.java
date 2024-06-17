package com.ecommerce.usermanagement.model;

import java.math.BigInteger;

import org.hibernate.annotations.Type;

import com.ecommerce.usermanagement.utility.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class UserImage {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long userImgId;
		
		private BigInteger userId;

	    private String picName;

	    private String picType;

	    @Lob
	    private byte[] imageData;
}
