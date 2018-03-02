package com.device.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.device.dao.DeviceDao;
import com.device.services.DeviceService;

@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceDao agentDao;

	@Override
	public Map<String, Object> getAgentByHotelId(String hotelId) {
		int count = 0;
		Map<String, Object> map = new HashMap<String,Object>();
		List<AgentList> agentList = new ArrayList<>();
		List<HotelAgentList> hotelAgentList = agentDao.getHotelAgentListByHotelId(hotelId);
		for (HotelAgentList i : hotelAgentList) {
			AgentList agent = agentDao.getAgentListByAgentId(i.getIdAgentList());
			agentList.add(agent);
			count++;
		}
		map.put("data", agentList);
		map.put("count", count);
		return map;
	}

}
