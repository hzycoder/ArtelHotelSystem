package com.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.SysUser;
import com.login.dto.LoginDto;

public interface LoginDao {
	public SysUser getUser(LoginDto loginDto);
	public boolean existUser(LoginDto loginDto);
	public List<SysUser> getUserList(int maxResult,int firstResult,int permission);
	public Integer getUserCount(int permission);
}
