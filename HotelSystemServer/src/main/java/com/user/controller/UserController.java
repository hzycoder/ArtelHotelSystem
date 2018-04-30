package com.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.common.base.BaseController;
import com.user.dto.LoginDto;
import com.user.dto.registerDto;
import com.user.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "getAllUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Map<String, Object> getAllUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = userService.getAllUser();
			msg = "获取数据成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("success", success);
			map.put("msg", msg);
			return map;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "getUnbindedUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Map<String, Object> getUnbindedUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = userService.getUnbindedUser();
			msg = "获取数据成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("success", success);
			map.put("msg", msg);
			return map;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "requestCode", method = RequestMethod.GET)
	public Map<String, Object> requestCode(String account) throws IOException {
		logger.info("接收到的参数" + account);
		Map<String, Object> map = null;
		try {
			map = userService.generateCode(account);
			success = true;
			msg = "请求成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
			return map;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> login(@RequestBody LoginDto loginDto) throws IOException {
		logger.info("Logining:" + loginDto.toString());
		Map<String, Object> map = null;
		return userService.login(loginDto);
//		Map<String, Object> map = null;
//		try {
//			map = userService.login(loginDto);
//			msg = "请求数据成功";
//		} catch (Exception e) {
//			e.printStackTrace();
//			msg = "系统内部错误";
//			success = false;
//		} finally {
//			map.put("msg", msg);
//			map.put("success", success);
//			return map;
//		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> login(@RequestBody registerDto registerDto) throws IOException {
		Map<String, Object> map = new HashMap<>();
		try {
			userService.register(registerDto);
			success = true;
			msg = "注册成功";
			map.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
			return map;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "modifyPass", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> modifyPass(String id, String newPass, String obsoletePass) throws Exception {
//		System.out.println("接收的数据："+id+"---"+newPass+"---"+obsoletePass);
		Map<String, Object> map = null;
		try {
			map = userService.modifyPass(id, newPass, obsoletePass);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
			map.put("msg", msg);
			map.put("success", success);
		}
		System.out.println(map.toString());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "modifyUserInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotels(String userName, String userPhone, String position, String userID)
			throws Exception {
		Map<String, Object> map = null;
		try {
			map = userService.modifyUser(userName, userPhone, position, userID);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
			map.put("msg", msg);
			map.put("success", success);
		}
		System.out.println(map.toString());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "getUserByID", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getUserByID(String userID) throws Exception {
		Map<String, Object> map = null;
		try {
			map = userService.getUserByUserID(userID);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	/**
	 * 验证密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "verifyPass", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> verifyPass(String userId, String password) throws Exception {
		System.out.println("userid:" + userId + "      password:" + password);
		Map<String, Object> map = null;
		try {
			map = userService.verifyPass(userId, password);
			success = true;
			msg = "验证完成";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
}