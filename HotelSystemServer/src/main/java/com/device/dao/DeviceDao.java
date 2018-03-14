package com.device.dao;

import java.util.List;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.common.pojo.RoomList;
import com.common.pojo.SoltList;
import com.device.dto.DeviceDto;

public interface DeviceDao {
	public List<AgentList> getAgentListByHotelId(String hotelId);
	public List<DeviceDto> getSoltByAgentId(String agentId);
}
