package com.user.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.pojo.LoginUserList;
import com.user.dao.UserDao;
import com.user.dto.LoginDto;
import com.user.dto.UserDto;
import com.user.dto.registerDto;
import com.user.services.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	LoginUserList user;
	@Autowired
	UserDao userDao;

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
			LoginUserList loginUserList = userDao.getUser(id);
			if (!judgePass(loginUserList, loginDto.getPassword())) {
//			if (!LoginUserList.getPassword().equals(loginDto.getPassword())) {
				map.put("error", "密码错误");
				return map;
			} else {
				UserDto user = new UserDto();
				try {
					BeanUtils.copyProperties(user, userDao.getUser(id));
				} catch (Exception e) {
					map.put("error", "系统内部错误");
					e.printStackTrace();
				} finally {
					map.put("result", user);
					return map;
				}
			}
		}
	}
	public static boolean judgePass(LoginUserList user,String toBeCheckedPass){
		String salt = user.getPasswordSalt();
		StringBuffer sb = new StringBuffer();
		String pass = DigestUtils.md5Hex(sb.append(toBeCheckedPass).append(salt).toString());
		if (pass.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String,Object> modifyUser(String userName, String userPhone, String position, String userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		int result = userDao.modifyUser(userName, userPhone, position, userId);
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

	@Override
	public Map<String, Object> verifyPass(String userId, String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean result = false;
		String pass = userDao.getPassByUserId(userId);
		if (pass.equals(password)) {
			result = true;
		}
		map.put("data", result);
		return map;
	}

	@Override
	public void register(registerDto registerDto) {
		LoginUserList user = new LoginUserList();
		//生成时间戳盐
		long time = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		//生成密码
		sb.append(registerDto.getPassword()).append(DigestUtils.md5Hex(String.valueOf(time)));
		user.setPasswordSalt(DigestUtils.md5Hex(String.valueOf(time)));
		user.setAccount(registerDto.getAccount());
		user.setPassword(DigestUtils.md5Hex(sb.toString()));
		user.setUserName(registerDto.getUserName());
		user.setCreateTime(new Timestamp(new Date().getTime()));
		user.setPermission(1);
		userDao.register(user);
	}
}
