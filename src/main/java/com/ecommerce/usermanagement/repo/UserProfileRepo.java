package com.ecommerce.usermanagement.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.UserProfile;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, BigInteger> {
	
	@Query("SELECT n FROM UserProfile n WHERE n.userPhoneNumber=:userPhoneNumber")
	UserProfile findByNumber(@Param("userPhoneNumber")String userPhoneNumber);

}
