package com.ecommerce.usermanagement.model;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger pId;
	private String pName;
	private String pDesc;
	private String pPhoto;
	private String pPrice;
	private String pDiscount;
	private String pQuantity;
	private BigInteger categoryFk;
}
