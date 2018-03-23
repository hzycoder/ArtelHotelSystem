package com.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.pojo.LoginUserList;
import com.user.dto.LoginDto;

public interface UserDao {
	public List<LoginUserList> getAllUser();
	public Integer getUserCount();
	public LoginUserList getUser(Integer id);
	public Integer existUser(LoginDto loginDto);
	public List<LoginUserList> getUserList(int maxResult,int firstResult,int permission);
	public Integer getUserCount(int permission);
	public Integer modifyUser(String userName,String userPhone,String position,String userID);
	public LoginUserList getUserByUserID(String userID);
}
