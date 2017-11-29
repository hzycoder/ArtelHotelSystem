package com.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.pojo.User;

@Controller
public class LoginController {
	private boolean success;
	private String msg;
	
	@PostMapping("login")
	@ResponseBody
	public Map<?, ?> login(@RequestBody User u, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("登录！获取前端数据"+u.toString());
		Map<String,Object>map = new HashMap<>();
		if (u.getAccount().equals("admin")&&u.getPassword().equals("admin")) {
			success = true;
			msg = "登录成功";
			u.setAccount("hello");
			u.setPassword("hello");
			map.put("result", u);
		}else {
			success = false;
			msg = "登录失败";
		}
		map.put("success", success);
		map.put("msg", msg);
		return map;
	}

}
