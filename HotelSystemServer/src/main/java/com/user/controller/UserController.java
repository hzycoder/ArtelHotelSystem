package com.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.common.base.BaseController;
import com.common.pojo.LoginUserList;
import com.user.services.UserService;

@Controller
public class UserController extends BaseController{
	@Autowired
	UserService userService;
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "getAllUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Map<String,Object> getAllUser(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map = userService.getAllUser();
			msg = "获取数据成功！";
			success = true;
			System.out.println("sysout:"+map.toString());
		} catch (Exception e) {
			e.printStackTrace();//注意不要漏了
			msg = e.getMessage();
			success = false;
			// TODO: handle exception
		}finally {
			map.put("success", success);
			map.put("msg", msg);
			return map;
		}
	}
}
