package com.hotel.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseController;
import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.hotel.services.HotelService;
import com.tcp.ChannelSession;

import io.netty.channel.Channel;

@Controller
@RequestMapping("/hotel")
public class HotelController extends BaseController {
	private static final Logger logger = Logger.getLogger(HotelController.class);
	@Autowired
	private HotelService hotelService;

	@ResponseBody
	@RequestMapping(value = "getHotels", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotels(String userID, String permission) throws Exception {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		
//		Map<String, Channel> channelMap = ChannelSession.getChannels();
//		Iterator<String> it = channelMap.keySet().iterator();
//		while (it.hasNext()) {
//			String key = it.next();
//			Channel channel = channelMap.get(key);
//			logger.info("Channel id is " + key);
//			logger.info("channel:" + channel.isActive());
//			channel.writeAndFlush("酒店发送消息");
//		}
		
		// WebSocketHandlerSession.print();
		// WebSocketHandlerAgentIdSession.print();
		// AssociatedSession.print();
		// if (WebSocketHandlerAgentIdSession.containKey("1")) {//
		// 判断WebSocketHandlerAgentIdSession中是否有该中继的通道
		// String sessionId = AssociatedSession.getSeesionId("1");//
		// 找出关联的sessionId
		// if (sessionId != null) {
		// WebSocketSession session = WebSocketHandlerSession.get(sessionId);
		// if (session != null) {
		// session.sendMessage(new TextMessage("酒店给你发消息了！！！！"));
		// }
		// }
		// }
		Map<String, Object> map = null;
		try {
			map = hotelService.getHotels(userID, permission);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "getHotelsByConditions", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotelsByConditions(String hotelName, String address, String userID, String permission)
			throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.getHotelsByConditions(hotelName, address, userID, permission);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "delHotel", method = RequestMethod.POST)
	public Map<String, Object> delHotel(String hotelID) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.delHotel(hotelID);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误!";
			map.put("msg", msg);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "addHotel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> addHotel(@RequestBody HotelDto hotelDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelService.addHotel(hotelDto);
			msg = "保存成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "modifyHotel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> modifyHotel(@RequestBody HotelDto hotelDto) throws Exception {
		System.out.println("修改酒店：" + hotelDto.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelService.modifyHotel(hotelDto);
			msg = "保存成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "addRoom", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> addRoom(@RequestBody RoomDto roomDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelService.addRoom(roomDto);
			msg = "保存成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误！";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "modifyRoom", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> modifyRoom(@RequestBody RoomDto roomDto) throws Exception {
		System.out.println("修改房间：" + roomDto.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelService.modifyRoom(roomDto);
			msg = "保存成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "batchAddRoom", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> batchAddRoom(@RequestBody RoomDto roomDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			hotelService.batchAddRoom(roomDto);
			msg = "保存成功！";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "系统内部错误！";
			success = false;
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "getRooms", method = RequestMethod.GET)
	public Map<String, Object> getRooms(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.getRooms(hotelId);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误!";
			map.put("msg", msg);
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "getUnbindedRooms", method = RequestMethod.GET)
	public Map<String, Object> getUnbindedRooms(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.getUnbindedRooms(hotelId);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误!";
			map.put("msg", msg);
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "delRoom", method = RequestMethod.POST)
	public Map<String, Object> delRoom(String roomID) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.delRoom(roomID);
			success = false;
			msg = "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			success = true;
			msg = "系统内部错误!";
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	/**
	 * 通过查询中继下的设备数量，判断是公寓或酒店
	 * 
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getTypeOfHotel", method = RequestMethod.GET)
	public Map<String, Object> getTypeOfHotel(String hotelId) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.getTypeOfHotel(hotelId);
			msg = "获取数据成功";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误!";
			map.put("msg", msg);
		} finally {
			map.put("msg", msg);
			map.put("success", success);
		}
		return map;
	}

	/**
	 * @param hotelName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "verifyHotelName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public boolean verifyHotelName(String hotelName) throws Exception {
		System.out.println("验证：" + hotelName);
		boolean b = false;
		try {
			b = hotelService.verifyHotelName(hotelName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结果：" + b);
		return b;
	}

	@ResponseBody
	@RequestMapping(value = "verifyRoomNum", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public boolean verifyRoomNum(String roomNum, Integer hotelId) throws Exception {
		System.out.println("验证：" + roomNum + "=====" + hotelId);
		boolean b = false;
		try {
			b = hotelService.verifyRoomNum(roomNum,hotelId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结果：" + b);
		return b;
	}

}
