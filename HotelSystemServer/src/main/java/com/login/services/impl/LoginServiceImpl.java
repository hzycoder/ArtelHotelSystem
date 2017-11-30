package com.login.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
//		数据库获取的数据转为dto
		UserDto user = new UserDto();
		try {
			BeanUtils.copyProperties(user, loginDao.getUser(loginDto));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("user"+user.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",user);
		System.out.println("JSON dto"+JSON.toJSONString(user));
		return map;
	}

}
