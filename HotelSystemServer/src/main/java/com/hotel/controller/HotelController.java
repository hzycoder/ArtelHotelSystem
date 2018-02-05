package com.hotel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.hotel.services.HotelService;
import com.login.dto.UserDto;

@Controller
public class HotelController extends BaseController {
	@Autowired
	HotelService hotelService;

	@ResponseBody
	@RequestMapping(value = "getHotels", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotels(@RequestBody UserDto user) throws Exception {
		System.out.println("id"+user.getId());
		Map<String, Object> map = null;
		try {
			map = hotelService.getHotels(user);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
			// TODO: handle exception
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

}
