package com.login.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojo.SysUser;
import com.login.dao.LoginDao;
import com.login.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	@Override
	public Map<String, Object> login(LoginDto loginDto) {
		SysUser user = loginDao.getUser(loginDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",user);
		return map;
	}

}
