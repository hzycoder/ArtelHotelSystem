package com.device.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojo.AgentList;
import com.device.dao.DeviceDao;
import com.device.dto.DeviceDto;
import com.device.services.DeviceService;

@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceDao deviceDao;

	@Override
	public Map<String, Object> getAgentByHotelId(String hotelId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<AgentList> agentList = deviceDao.getAgentListByHotelId(hotelId);
		map.put("data", agentList);
		return map;
	}
	
	@Override
	public Map<String, Object> getSoltByAgentId(String agentId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<DeviceDto> list = deviceDao.getSoltByAgentId(agentId);
		map.put("data", list);
		return map;
	}

}
