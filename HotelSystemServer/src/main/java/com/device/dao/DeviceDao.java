package com.device.dao;

import java.util.List;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.common.pojo.RoomSoltList;
import com.common.pojo.SoltList;
import com.common.pojo.UpgradeFile;
import com.device.dto.AgentDto;
import com.device.dto.BindingAgentDto;
import com.device.dto.BindingHotelDto;
import com.device.dto.BindingRoomDto;
import com.device.dto.DeviceDto;

public interface DeviceDao {
	public List<AgentList> getAgentListByHotelId(String hotelId);
	public List<DeviceDto> getSoltByAgentId(String agentId);
	public Integer getAgentCount(String hotelId);
	public Integer getDeviceCount(String hotelId);
	public List<DeviceDto> getAgentByInn(String hotelId);
	public List<AgentDto> getAgentAndRoomRelations(String agentId);
	public Integer getslotIdByAgentId(String agentId);
	public BindingAgentDto getAgentBySlotId(String slotId);
	public BindingHotelDto getHotelByAgentId(String agentId);
	public BindingRoomDto getRoomBySlotId(String SlotId);
	public RoomSoltList getRoomSoltListBySlotId(Integer slotId);
	public HotelAgentList getHotelAgentListByAgentId(Integer agentId);
	public SoltList getSlotListBySlotId(Integer slotId);
	public void updateRoomSlotList(RoomSoltList roomSoltList);
	public void updateHotelAgentList(HotelAgentList hotelAgentList);
	public void updateRoomList(Integer roomID,Integer subNet);
	public void saveFile(UpgradeFile file);
	public void saveInstance(Object o);
}
