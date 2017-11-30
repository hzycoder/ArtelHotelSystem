package com.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.login.dto.LoginDto;
import com.login.services.LoginService;

@Controller
public class LoginController {
	private boolean success;
	private String msg;
	@Autowired
	LoginService loginService;

//	@PostMapping("login")
	@RequestMapping(value="login",method=RequestMethod.POST,produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("��¼����ȡǰ������" + loginDto.toString());
		Map<String, Object> map = null;
		map = loginService.login(loginDto);
		// if
		// (u.getAccount().equals("admin")&&loginDto.getPassword().equals("admin"))
		// {
		// success = true;
		// msg = "��¼�ɹ�";
		// u.setAccount("hello");
		// u.setPassword("hello");
		// map.put("result", u);
		// }else {
		// success = false;
		// msg = "��¼ʧ��";
		// }
		map.put("success", success);
		map.put("msg", msg);
		System.out.println("map:" + map.toString());
		return map;
	}

}
