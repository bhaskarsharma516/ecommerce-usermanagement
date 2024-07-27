package com.ecommerce.usermanagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.usermanagement.model.UserProfile;
import com.ecommerce.usermanagement.repo.UserProfileRepo;
import com.ecommerce.usermanagement.service.UserService;
import com.ecommerce.usermanagement.utility.Role;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserProfileRepo userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;



	@Override
	public UserProfile saveUser(UserProfile user) {
		// TODO Auto-generated method stub
		user.setUserRole(Role.USER);
		user.setIsEnable(true);
		user.setAccountNonLocked(true);
		user.setFailedAttempt(0);

		String encodePassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodePassword);
		return userRepository.save(user);
		
		
	}

	@Override
	public UserProfile getUserByNumber(String phoneNumber) {
		return userRepository.findByNumber(phoneNumber);
	
	}

	@Override
	public List<UserProfile> getUsers(String role) {
		// TODO Auto-generated method stub
		return userRepository.findByUserRole(role);

	}

	@Override
	public Boolean updateAccountStatus(Integer id, Boolean status) {
		Optional<UserProfile> findByuser = userRepository.findById(id);

		if (findByuser.isPresent()) {
			UserProfile userDtls = findByuser.get();
			userDtls.setIsEnable(status);
			userRepository.save(userDtls);
			return true;
		}

		return false;
	}

	@Override
	public void increaseFailedAttempt(UserProfile user) {
		// TODO Auto-generated method stub
		int attempt = user.getFailedAttempt() + 1;
		user.setFailedAttempt(attempt);
		userRepository.save(user);

	}

	@Override
	public void userAccountLock(UserProfile user) {
		// TODO Auto-generated method stub
		user.setAccountNonLocked(false);
		user.setLockTime(new Date());
		userRepository.save(user);
	}

	@Override
	public boolean unlockAccountTimeExpired(UserProfile user) {
		long lockTime = user.getLockTime().getTime();
		long unLockTime = lockTime +3000;

		long currentTime = System.currentTimeMillis();

		if (unLockTime < currentTime) {
			user.setAccountNonLocked(true);
			user.setFailedAttempt(0);
			user.setLockTime(null);
			userRepository.save(user);
			return true;
		}

		return false;

	}

	@Override
	public void resetAttempt(int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserResetToken(String number, String resetToken) {
		// TODO Auto-generated method stub
		UserProfile findByEmail = userRepository.findByNumber(number);
		findByEmail.setResetToken(resetToken);
		userRepository.save(findByEmail);

	}

	@Override
	public UserProfile getUserByToken(String token) {
		return userRepository.findByResetToken(token);
	}

	@Override
	public UserProfile updateUser(UserProfile user) {
		return userRepository.save(user);
	}

}
