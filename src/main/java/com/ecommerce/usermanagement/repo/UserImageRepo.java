package com.ecommerce.usermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usermanagement.model.UserImage;

@Repository
public interface UserImageRepo  extends JpaRepository<UserImage, Long> {

}
