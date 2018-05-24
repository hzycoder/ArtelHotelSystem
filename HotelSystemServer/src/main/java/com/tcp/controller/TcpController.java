package com.tcp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.tcp.services.TcpService;

@Controller
@RequestMapping("/tcp")
public class TcpController extends BaseController {
	@Autowired
	TcpService tcpService;

	@ResponseBody
	@RequestMapping(value = "upgrade", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> upgrade(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tcpService.upgrade(hotelId);
			success = true;
			msg = "发送成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "发送失败！";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "roomOperation", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> roomOperation(String json) {
		System.out.println(json);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			success = tcpService.roomOperation(json);
			msg = "发送成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "发送失败！";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
}
