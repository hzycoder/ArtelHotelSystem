package com.user.dao;

import java.util.List;

import com.common.pojo.LoginUserList;
import com.user.dto.LoginDto;

public interface UserDao {
	public List<LoginUserList> getAllUser();
	public Integer getUserCount();
	public LoginUserList getUser(Integer id);
	public Integer existUser(LoginDto loginDto);
	public Integer modifyUser(String userName,String userPhone,String position,String userID);
	public LoginUserList getUserByUserID(String userID);
	public String getPassByUserId(String userID);
	public void register(LoginUserList user);
}
