package com.ecommerce.usermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.ProductImage;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {

}
