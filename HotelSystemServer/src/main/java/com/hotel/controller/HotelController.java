package com.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.BaseController;
import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.hotel.services.HotelService;
import com.login.dto.UserDto;

@Controller
public class HotelController extends BaseController {
	@Autowired
	private HotelService hotelService;

	@ResponseBody
	@RequestMapping(value = "getHotels", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	// public Map<String, Object> getHotels(@RequestBody UserDto user) throws
	// Exception {
	public Map<String, Object> getHotels() throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.getHotels();
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
	@RequestMapping(value = "getHotelByConditions", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public Map<String, Object> getHotelsByConditions(String hotelName, String address) throws Exception {
		System.out.println(hotelName + "---" + address);
		Map<String, Object> map = null;
		try {
			map = hotelService.getHotelsByConditions(hotelName, address);
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
			msg = e.getMessage();
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
		System.out.println("roomDto::::" + roomDto.toString());
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
	@RequestMapping(value = "getRooms", method = RequestMethod.GET)
	public Map<String, Object> getRooms(String hotelId) throws Exception {
		System.out.println("getRoom:" + hotelId);
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
	@RequestMapping(value = "delRoom", method = RequestMethod.POST)
	public Map<String, Object> delRoom(String roomID) throws Exception {
		Map<String, Object> map = null;
		try {
			map = hotelService.delRoom(roomID);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			msg = "系统内部错误!";
			map.put("msg", msg);
		}
		return map;
	}
}
