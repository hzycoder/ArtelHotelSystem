package com.device.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.common.base.BaseController;
import com.device.services.DeviceService;

@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController {
	private static final Logger logger = Logger.getLogger(DeviceController.class);
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
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	/**
	 * 获取酒店视图中所需的soltList
	 * 
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
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public Map<String, Object> uploadUpgradeFile(MultipartFile uploadFile, MultipartHttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> fileNameIterator = request.getFileNames();
		while (fileNameIterator.hasNext()) {
			String string = (String) fileNameIterator.next();
			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) request.getFile(string);
			try {
				deviceService.saveFile(commonsMultipartFile);
				success = true;
				msg = "上传文件成功！";
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = "系统内部错误";
			} finally {
				map.put("msg", msg);
				map.put("success", success);
			}
		}
		return map;
	}

	/**
	 * 获取客栈视图中所需的agentList
	 * 
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
			msg = "系统内部错误";
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
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	/**
	 * 判断中继是否绑定房间
	 * 
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getAgentAndRoomRelations", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getAgentAndRoomRelations(String agentId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getAgentAndRoomRelations(agentId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "getslotIdByAgentId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getslotIdByAgentId(String agentId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = deviceService.getslotIdByAgentId(agentId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "binding", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> binding(Integer roomId,Integer slotId) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			deviceService.binding(roomId,slotId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
}
