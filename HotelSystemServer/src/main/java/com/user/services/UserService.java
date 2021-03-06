package com.user.services;

import java.util.Map;

import com.user.dto.LoginDto;
import com.user.dto.registerDto;

public interface UserService {
	public Map<String, Object> getAllUser();

	public Map<String, Object> login(LoginDto loginDto);

	public Map<String, Object> modifyUser(String userName, String userPhone, String position, String userID);

	public Map<String, Object> getUserByUserID(String userId);

	public Map<String, Object> verifyPass(String userId, String password);

	public void register(registerDto registerDto);

	public Map<String, Object> getUnbindedUser();

	public Map<String, Object> generateCode(String account);

	public Map<String, Object> modifyPass(String id, String newPass, String obsoletePass);
}
