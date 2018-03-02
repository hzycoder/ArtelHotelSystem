package com.device.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.device.services.DeviceService;

@Controller
public class DeviceController extends BaseController{
	@Autowired
	DeviceService deviceService;

	@ResponseBody
	@RequestMapping(value = "getAgentByHotelId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getAgentByHotelId(String hotelId) throws Exception {
		Map<String,Object> map = null;
		try {
			map = deviceService.getAgentByHotelId(hotelId);
			success = true;
			msg="获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg=e.getMessage();
		}finally {
			map.put("msg", msg);
			map.put("success",success);
		}
		return map;
	}
}
