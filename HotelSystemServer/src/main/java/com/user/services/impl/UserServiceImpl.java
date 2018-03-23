package com.user.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.pojo.LoginUserList;
import com.user.dao.UserDao;
import com.user.dto.LoginDto;
import com.user.dto.UserDto;
import com.user.services.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	LoginUserList user;
	@Autowired
	UserDao userDao;

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> getAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<LoginUserList> userList = userDao.getAllUser();
		int count = userDao.getUserCount();
		map.put("count", count);
		map.put("data", userList);
		return map;
	}

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> login(LoginDto loginDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer id = userDao.existUser(loginDto); // 判断用户是否存在
		if (null == id) {
			map.put("error", "用户不存在");
			return map;
		} else {
			LoginUserList LoginUserList = userDao.getUser(id);
			if (!LoginUserList.getPassword().equals(loginDto.getPassword())) {
				map.put("error", "密码错误");
				return map;
			} else {
				UserDto user = new UserDto();
				try {
					BeanUtils.copyProperties(user, userDao.getUser(id));
				} catch (Exception e) {
					map.put("error", e.getMessage());
					e.printStackTrace();
				} finally {
					map.put("result", user);
					return map;
				}
			}
		}
	}

	@Override
	public Map<String,Object> modifyUser(String userName, String userPhone, String position, String userID) {
		Map<String,Object> map = new HashMap<String,Object>();
		int result = userDao.modifyUser(userName, userPhone, position, userID);
		if (result!=0) {
			map.put("msg", "修改数据成功！");
			map.put("success", true);
		}else {
			map.put("msg", "修改失败！");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserByUserID(String userID) {
		Map<String,Object> map = new HashMap<String,Object>();
		LoginUserList user = userDao.getUserByUserID(userID);
		user.setPassword("");
		user.setPasswordSalt("");
		map.put("data", user);
		return map;
	}
}
