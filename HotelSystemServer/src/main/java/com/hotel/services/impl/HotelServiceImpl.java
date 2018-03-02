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

import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.common.pojo.RoomList;
import com.hotel.dao.HotelDao;
import com.hotel.dto.HotelDto;
import com.hotel.services.HotelService;
import com.login.dto.UserDto;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDao hotelDao;
	@Autowired
	LoginUserList user;

	@Override
	public Map<String, Object> getHotels() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<HotelList> hotelList = hotelDao.gethotels();
		int count = hotelDao.getHotelsCount();
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
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getCity())) {
			hotelList.setCity(hotelDto.getCity());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getProvince())) {
			hotelList.setProvince(hotelDto.getProvince());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getAddress())) {
			hotelList.setAddress(hotelDto.getAddress());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelPhone())) {
			hotelList.setHotelPhone(hotelDto.getHotelPhone());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getRepeaterCount())) {
			hotelList.setRepeaterCount(hotelDto.getRepeaterCount());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getDeviceCount())) {
			hotelList.setDeviceCount(hotelDto.getDeviceCount());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelManager())) {
			hotelList.setHotelManager(hotelDto.getHotelManager());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getStatus())) {
			hotelList.setStatus(hotelDto.getStatus());
			;
		}
		if (StringUtils.isNotBlank(hotelDto.getHotelId())) {
			hotelList.setHotelId(hotelDto.getHotelId());
			;
		}
		hotelList.setCreateTime(new Timestamp(new Date().getTime()));
		hotelDao.addHotel(hotelList);
	}

	@Override
	public Map<String, Object> getRooms(String hotelId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoomList> roomList = hotelDao.getRooms(hotelId);
		int count = hotelDao.getRoomsCount(hotelId);
		map.put("data",roomList);
		map.put("count", count);
		return map;
	}
}
