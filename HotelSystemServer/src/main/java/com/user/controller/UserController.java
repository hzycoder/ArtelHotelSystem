package com.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.common.base.BaseController;
import com.common.pojo.LoginUserList;
import com.user.dto.LoginDto;
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
			e.printStackTrace();// 注意不要漏了
			msg = e.getMessage();
			success = false;
			// TODO: handle exception
		} finally {
			map.put("success", success);
			map.put("msg", msg);
			return map;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("接收到的参数" + loginDto.toString());
		Map<String, Object> map = null;
		try {
			map = userService.login(loginDto);
			if (null != map.get("error")) {
				success = false;
				return map;
			}
			success = true;
			msg = "登录成功";
			map.put("msg", msg);
		} catch (Exception e) {
			msg = e.getMessage();
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
			return map;
		}
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
}