package com.user.dao;

import java.util.List;

import com.common.pojo.SysUser;

public interface UserDao {
	public List<SysUser> getAllUser();
	public Integer getUserCount();
}
