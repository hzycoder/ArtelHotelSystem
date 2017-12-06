package com.hotel.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.pojo.SysHotel;
import com.common.pojo.SysUser;
import com.hotel.dao.HotelDao;
import com.hotel.services.HotelService;

public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDao hotelDao;
	@Autowired
	SysUser user;

	@Override
	public Map<String, Object> getHotels(String id) {
		Map<String, Object> map = null;
		user.setId(Integer.parseInt(id));
		List<SysHotel> hotelList = hotelDao.gethotels(user);
		map.put("result", hotelList);
		return map;
	}

}
