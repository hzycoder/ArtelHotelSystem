package com.hotel.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.pojo.SysHotel;
import com.common.pojo.SysUser;
import com.hotel.dao.HotelDao;
import com.hotel.services.HotelService;
import com.login.dto.UserDto;
@Transactional
@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDao hotelDao;
	@Autowired
	SysUser user;

	@Override
	public Map<String, Object> getHotels(UserDto user) {
		Map<String, Object> map = new HashMap<String, Object>();
			List<SysHotel> hotelList = hotelDao.gethotels(user);
			int count = hotelDao.getHotelCounts(user);
			map.put("result", hotelList);
			map.put("count", count);
			return map;
	}
}
