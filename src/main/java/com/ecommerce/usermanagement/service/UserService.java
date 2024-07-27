package com.ecommerce.usermanagement.service;

import java.util.List;

import com.ecommerce.usermanagement.model.UserProfile;

public interface UserService {
	
	public UserProfile saveUser(UserProfile user);

	public UserProfile getUserByNumber(String phoneNumber);

	public List<UserProfile> getUsers(String role);

	public Boolean updateAccountStatus(Integer id, Boolean status);

	public void increaseFailedAttempt(UserProfile user);

	public void userAccountLock(UserProfile user);

	public boolean unlockAccountTimeExpired(UserProfile user);

	public void resetAttempt(int userId);

	public void updateUserResetToken(String email, String resetToken);
	
	public UserProfile getUserByToken(String token);
	
	public UserProfile updateUser(UserProfile user);

}
