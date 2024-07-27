package com.ecommerce.usermanagement.model;

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
public class ProductImage {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Integer pIdFk;

    private String picName;

    private String picType;

    @Lob
    private byte[] imageData;

}
