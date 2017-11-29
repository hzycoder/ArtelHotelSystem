package com.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.dto.LoginDto;
import com.login.services.LoginService;

@Controller
public class LoginController {
	private boolean success;
	private String msg;
	@Autowired
	LoginService loginService;
	
	@PostMapping("login")
	@ResponseBody
	public Map<?, ?> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("登录！获取前端数据"+loginDto.toString());
		Map<String,Object> map = null;
		map = loginService.login(loginDto);
//		if (u.getAccount().equals("admin")&&loginDto.getPassword().equals("admin")) {
//			success = true;
//			msg = "登录成功";
//			u.setAccount("hello");
//			u.setPassword("hello");
//			map.put("result", u);
//		}else {
//			success = false;
//			msg = "登录失败";
//		}
		System.out.println("map:"+map.toString());
		
		map.put("success", success);
		map.put("msg", msg);
		return map;
	}

}
