package com.device.dao;

import java.util.List;

import com.common.pojo.AgentList;
import com.common.pojo.RoomSoltList;
import com.common.pojo.UpgradeFile;
import com.device.dto.AgentDto;
import com.device.dto.DeviceDto;

public interface DeviceDao {
	public List<AgentList> getAgentListByHotelId(String hotelId);
	public List<DeviceDto> getSoltByAgentId(String agentId);
	public Integer getAgentCount(String hotelId);
	public Integer getDeviceCount(String hotelId);
	public List<DeviceDto> getAgentByInn(String hotelId);
	public List<AgentDto> getAgentAndRoomRelations(String agentId);
	public Integer getslotIdByAgentId(String agentId);
	public void binding(RoomSoltList rsList);
	public void saveFile(UpgradeFile file);
}
