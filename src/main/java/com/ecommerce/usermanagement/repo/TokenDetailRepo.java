package com.ecommerce.usermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.TokenDetails;

@Repository
public interface TokenDetailRepo extends JpaRepository<TokenDetails, Long>{

	
	TokenDetails findByJwtToken(@Param("jwtToken")String jwtToken);

}
