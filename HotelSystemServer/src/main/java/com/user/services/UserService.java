package com.user.services;

import java.util.Map;

import com.user.dto.LoginDto;

public interface UserService {
	public Map<String, Object> getAllUser();

	public Map<String, Object> login(LoginDto loginDto);

	public Map<String, Object> modifyUser(String userName, String userPhone, String position, String userID);

	public Map<String, Object> getUserByUserID(String userId);

	public Map<String, Object> verifyPass(String userId, String password);
}
