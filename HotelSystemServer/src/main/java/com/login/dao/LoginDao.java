package com.login.dao;

import java.util.List;
import com.common.pojo.LoginUserList;
import com.login.dto.LoginDto;

public interface LoginDao {
//	public List<?> getUserInfo(Integer id);
	public LoginUserList getUser(Integer id);
	public Integer existUser(LoginDto loginDto);
	public List<LoginUserList> getUserList(int maxResult,int firstResult,int permission);
	public Integer getUserCount(int permission);
}
