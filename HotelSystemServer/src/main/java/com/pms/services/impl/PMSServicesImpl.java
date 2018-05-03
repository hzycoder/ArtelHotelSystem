package com.pms.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.dao.PMSDao;
import com.pms.dto.HotelDto;
import com.pms.dto.RoomStatusDto;
import com.pms.services.PMSServices;


@Transactional
@Service
public class PMSServicesImpl implements PMSServices {
	@Autowired
	PMSDao pmsDao;

	@Override
	public Map<String, Object> getHotelList(Integer pmsId) {
		Map<String ,Object> map = new HashMap<String,Object>();
		List<HotelDto> hotelList = pmsDao.getHotelList(1);
		map.put("data", hotelList);
		return map;
	}

	@Override
	public Map<String, Object> getRommStatusListByHotelCode(String hotelCode) {
		Map<String ,Object> map = new HashMap<String,Object>();
		List<RoomStatusDto> roomStatusList = pmsDao.getRommStatusListByHotelCode(hotelCode);
		map.put("data", roomStatusList);
		return map;
	}

	@Override
	public Map<String, Object> getRoomStatusByRoomCode(String roomCode) {
		Map<String ,Object> map = new HashMap<String,Object>();
		RoomStatusDto roomStatus = pmsDao.getRoomStatusByRoomCode(roomCode);
		map.put("data", roomStatus);
		return map;
	}

}
