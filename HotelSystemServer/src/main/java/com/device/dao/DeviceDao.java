package com.device.dao;

import java.util.List;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;

public interface DeviceDao {
	public List<HotelAgentList> getHotelAgentListByHotelId(String hotelId);
	public AgentList getAgentListByAgentId(int agentId);
}
