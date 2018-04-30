package com.user.dao;

import java.util.List;

import com.common.pojo.LoginUserList;
import com.user.dto.LoginDto;
import com.user.dto.UserDto;

public interface UserDao {
	public List<LoginUserList> getAllUser();

	public List<UserDto> getUnbindedUser();

	public LoginUserList getUserById(Integer id);

	public Integer existUser(LoginDto loginDto);

	public Integer modifyUser(String userName, String userPhone, String position, String userID);

	public String getPassByUserId(String userID);

	public void register(LoginUserList user);

	public Integer modifyPass(String id, String obsoletePass);
}
