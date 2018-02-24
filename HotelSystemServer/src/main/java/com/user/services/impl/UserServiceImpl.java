package com.user.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.pojo.SysUser;
import com.user.dao.UserDao;
import com.user.services.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	SysUser user;
	@Autowired
	UserDao userDao;

	@SuppressWarnings("finally")
	@Override
	public Map<String,Object> getAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysUser> userList = userDao.getAllUser();
		int count = userDao.getUserCount();
		map.put("count",count);
		map.put("result", userList);
		return map;
	}

}
