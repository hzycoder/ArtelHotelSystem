package com.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.login.dto.LoginDto;
import com.login.services.LoginService;

@Controller
public class LoginController extends BaseController{
	@Autowired
	LoginService loginService;

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("接收到的参数" + loginDto.toString());
		Map<String, Object> map = null;
		map = loginService.login(loginDto);
		success = true;
		msg = "登录成功";
		map.put("success", success);
		map.put("msg", msg);
		// response.setCharacterEncoding("utf-8");
		// response.getWriter().write(JSON.toJSONString(map));
		// response.getWriter().close();
		return map;

	}

}
