package com.login.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.common.pojo.SysUser;
import com.login.dao.LoginDao;
import com.login.dto.LoginDto;
import com.login.dto.UserDto;
import com.login.services.LoginService;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	@Override
	public Map<String, Object> login(LoginDto loginDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer id = loginDao.existUser(loginDto); // 判断用户是否存在
		System.out.println("====id:"+id);
		if (null != id) {// 用户存在
			UserDto user = new UserDto();
			try {
				BeanUtils.copyProperties(user, loginDao.getUser(id));
			} catch (Exception e) {
				map.put("error", e.getMessage());
				e.printStackTrace();
				return map;
			}
			map.put("result", user);
			System.out.println(map.toString());
			return map;
		} else {// 用户不存在
			map.put("error", "用户不存在");
			return map;
		}

	}

}
