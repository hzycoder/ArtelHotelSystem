package com.hotel.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import com.common.dao.CommonDao;
import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.common.pojo.RoomList;
import com.hotel.dao.HotelDao;
import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.hotel.services.HotelService;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDao hotelDao;
	@Autowired
	LoginUserList user;
	@Autowired
	CommonDao commonDao;

	// 酒店查询
	@Override
	public Map<String, Object> getHotels(String userID, String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HotelList> hotelList = null;
		String sql = "";
		if (Integer.valueOf(permission) == 0) {
			hotelList = hotelDao.gethotels();
			sql = "SELECT COUNT(*) FROM HotelList";
		} else {
			hotelList = hotelDao.gethotels(userID);
			sql = "SELECT COUNT(*) FROM HotelList WHERE hotelManager = '" + userID + "'";
		}
		int count = commonDao.getCount(sql);
		map.put("data", hotelList);
		map.put("count", count);
		return map;
	}

	// 酒店条件查询
	@Override
	public Map<String, Object> getHotelsByConditions(String hotelName, String address, String userID,
			String permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HotelList> hotelList;
		String sql = "";
		if (Integer.valueOf(permission) == 0) {
			hotelList = hotelDao.gethotelsByConditions(hotelName, address);
			sql = "SELECT COUNT(*) FROM HotelList WHERE hotelName like '%" + hotelName + "%' and address like '%"
					+ address + "%'";
		} else {
			hotelList = hotelDao.gethotelsByConditions(hotelName, address, userID);
			sql = "SELECT COUNT(*) FROM HotelList WHERE hotelName like '%" + hotelName + "%' and address like '%"
					+ address + "%' and hotelManager = '" + userID + "'";
		}
		int count = commonDao.getCount(sql);
		map.put("data", hotelList);
		map.put("count", count);
		return map;
	}

	@Override
	public Map<String, Object> delHotel(String HotelID) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = hotelDao.delHotel(HotelID);
		if (result == 0) {
			map.put("msg", "删除失败！");
			map.put("success", false);
		} else {
			map.put("msg", "删除成功！");
			map.put("success", true);
		}
		return map;
	}

	@Override
	public void addHotel(HotelDto hotelDto) {
		HotelList hotelList = new HotelList();
		if (StringUtils.isNotBlank(hotelDto.getHotelName())) {
			hotelList.setHotelName(hotelDto.getHotelName());
		}
		if (StringUtils.isNotBlank(hotelDto.getCountry())) {
			hotelList.setCountry(hotelDto.getCountry());
		}
		if (StringUtils.isNotBlank(hotelDto.getCity())) {
			hotelList.setCity(hotelDto.getCity());
		}
		if (StringUtils.isNotBlank(hotelDto.getProvince())) {
			hotelList.setProvince(hotelDto.getProvince());
		}
		if (StringUtils.isNotBlank(hotelDto.getAddress())) {
			hotelList.setAddress(hotelDto.getAddress());
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelPhone())) {
			hotelList.setHotelPhone(hotelDto.getHotelPhone());
		}
		if (StringUtils.isNotBlank(hotelDto.getRepeaterCount())) {
			hotelList.setRepeaterCount(hotelDto.getRepeaterCount());
		}
		if (StringUtils.isNotBlank(hotelDto.getDeviceCount())) {
			hotelList.setDeviceCount(hotelDto.getDeviceCount());
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelManager())) {
			hotelList.setHotelManager(hotelDto.getHotelManager());
		}
		if (StringUtils.isNotBlank(hotelDto.getStatus())) {
			hotelList.setStatus(hotelDto.getStatus());
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelId())) {
			hotelList.setHotelId(hotelDto.getHotelId());
		}
		if (StringUtils.isNotBlank(hotelDto.getIdHotelList())) {
			hotelList.setIdHotelList(Integer.valueOf(hotelDto.getIdHotelList()));
		}
		hotelList.setCreateTime(new Timestamp(new Date().getTime()));
		hotelDao.addHotel(hotelList);
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		RoomList roomList = new RoomList();
		int roomCount = hotelDao.getRoomCountByHotelID(roomDto.getHotelId());
		roomList.setRoomId(roomDto.getHotelNum() + "_" + String.valueOf(roomCount));
		roomList.setHotelId(roomDto.getHotelId());
		if (StringUtils.isNotBlank(roomDto.getIdRoomList())) {
			roomList.setIdRoomList(Integer.valueOf(roomDto.getIdRoomList()));
		}
		if (StringUtils.isNotBlank(roomDto.getFloor())) {
			roomList.setFloor(roomDto.getFloor());
		}
		if (StringUtils.isNotBlank(roomDto.getRoomId())) {
			roomList.setRoomId(roomDto.getRoomId());
		}
		if (StringUtils.isNotBlank(roomDto.getRoomNum())) {
			roomList.setRoomNum(roomDto.getRoomNum());
		}
		hotelDao.addRoom(roomList);
	}

	@Override
	public Map<String, Object> getRooms(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoomList> roomList = hotelDao.getRooms(hotelId);
		int count = hotelDao.getRoomsCount(hotelId);
		map.put("data", roomList);
		map.put("count", count);
		return map;
	}

	@Override
	public Map<String, Object> delRoom(String roomID) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = hotelDao.delRoom(roomID);
		if (result == 0) {
			map.put("msg", "删除失败！");
			map.put("success", false);
		} else {
			map.put("msg", "删除成功！");
			map.put("success", true);
		}
		return map;
	}

}
