package com.login.services.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.common.pojo.LoginUserList;
import com.login.dao.LoginDao;
import com.login.dto.LoginDto;
import com.login.dto.UserDto;
import com.login.services.LoginService;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> login(LoginDto loginDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer id = loginDao.existUser(loginDto); // 判断用户是否存在
		System.out.println("====id:"+id);
		if (null == id) {
			map.put("error", "用户不存在");
			return map;
		} else {
			LoginUserList LoginUserList = loginDao.getUser(id);
			if (!LoginUserList.getPassword().equals(loginDto.getPassword())) {
				map.put("error", "密码错误");
				return map;
			}else {
				UserDto user = new UserDto();
				try {
					BeanUtils.copyProperties(user, loginDao.getUser(id));
				} catch (Exception e) {
					map.put("error", e.getMessage());
					e.printStackTrace();
				}finally {
					map.put("result", user);
					System.out.println(user.toString());
					return map;
				}
			}
		}
	}
}
