package com.user.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.pojo.LoginUserList;
import com.common.util.CharacterUtils;
import com.common.util.MyException;
import com.user.LoginSession;
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
	private final int NORMAL_PERMISSION = 1;

	@Override
	public Map<String, Object> getAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<LoginUserList> userList = userDao.getAllUser();
		int count = userList.size();
		map.put("count", count);
		map.put("data", userList);
		return map;
	}

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> login(LoginDto loginDto) {
		boolean success = false;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer id = userDao.existUser(loginDto);
			if (null == id) {
				throw new MyException("用户不存在");
			}
			LoginUserList loginUserList = userDao.getUserById(id);
			if (!judgePass(loginUserList, loginDto.getPassword())) {
				throw new MyException("密码错误");
			}
			UserDto user = new UserDto();
			BeanUtils.copyProperties(user, loginUserList);
			success = true;
			map.put("data", user);
		} catch (MyException e) {
			map.put("error", e.getMessage());
			success = false;
		} catch (Exception e) {
			map.put("error", "系统内部错误");
			success = false;
		} finally {
			map.put("success", success);
		}
		return map;
	}

	public static boolean judgePass(LoginUserList loginUserList, String toBeCheckedPass) {
		String code = LoginSession.getCodeById(loginUserList.getAccount());
		StringBuffer sb = new StringBuffer();
		String tempPass = (sb.append(code).append(loginUserList.getPassword())).toString();
		String pass = DigestUtils.md5Hex(tempPass);
		if (pass.equals(toBeCheckedPass)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, Object> modifyUser(String userName, String userPhone, String position, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = userDao.modifyUser(userName, userPhone, position, userId);
		if (result != 0) {
			map.put("msg", "修改数据成功！");
			map.put("success", true);
		} else {
			map.put("msg", "修改失败！");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserByUserID(String userID) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUserList loginUserList = userDao.getUserById(Integer.valueOf(userID));
		UserDto user = new UserDto();
		try {
			BeanUtils.copyProperties(user, loginUserList);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		map.put("data", user);
		return map;
	}

	@Override
	public Map<String, Object> verifyPass(String userId, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
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
		// 生成时间戳盐
		long time = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		// 生成密码
		user.setPasswordSalt(null);
		user.setPassword(DigestUtils.md5Hex(registerDto.getPassword()));
		user.setAccount(registerDto.getAccount());
		user.setUserName(registerDto.getUserName());
		user.setCreateTime(String.valueOf(time));
		user.setPermission(NORMAL_PERMISSION);
		user.setCreator(registerDto.getCreator());
		userDao.register(user);
	}

	@Override
	public Map<String, Object> getUnbindedUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserDto> userList = userDao.getUnbindedUser();
		int count = userList.size();
		map.put("count", count);
		map.put("data", userList);
		return map;
	}

	@Override
	public Map<String, Object> generateCode(String account) {
		Map<String, Object> map = new HashMap<>();
		String code = CharacterUtils.getRandomString(5);
		LoginSession.addLoginSession(account, code);// 生成随机码
		LoginSession.print();
		map.put("data", code);
		return map;
	}

	@Override
	public Map<String, Object> modifyPass(String id, String newPass, String obsoletePass) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUserList user = userDao.getUserById(Integer.valueOf(id));
		if (!obsoletePass.equals(user.getPassword())) {
			map.put("msg", "密码错误!");
			map.put("success", false);
			return map;
		}
		Integer flag = userDao.modifyPass(id, newPass);
		if (flag != 0) {
			map.put("msg", "修改密码成功！");
			map.put("success", true);
		} else {
			map.put("msg", "修改密码失败！");
			map.put("success", false);
		}
		return map;
	}
}
