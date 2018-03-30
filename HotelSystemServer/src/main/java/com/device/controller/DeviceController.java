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
@RequestMapping("/device")
public class DeviceController extends BaseController {
	@Autowired
	DeviceService deviceService;

	@ResponseBody
	@RequestMapping(value = "getAgentByInn", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getAgentByInn(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getAgentByInn(hotelId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = e.getMessage();
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	
	/**
	 * 获取酒店视图中所需的soltList
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getSoltByAgentId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getSoltByAgentId(String agentId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getSoltByAgentId(agentId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = e.getMessage();
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
	
	/**
	 * 获取客栈视图中所需的agentList
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getAgentByHotelId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getAgentByHotelId(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getAgentByHotelId(hotelId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = e.getMessage();
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "getDeviceCountByHotelId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getDeviceCountByHotelId(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getDeviceCountByHotelId(hotelId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = e.getMessage();
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
}
