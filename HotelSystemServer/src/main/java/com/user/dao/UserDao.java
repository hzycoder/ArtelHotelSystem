package com.user.dao;

import java.util.List;

import com.common.pojo.LoginUserList;

public interface UserDao {
	public List<LoginUserList> getAllUser();
	public Integer getUserCount();
}
