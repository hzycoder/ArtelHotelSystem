package com.device.services;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface DeviceService {
	public Map<String, Object> getAgentByHotelId(String hotelId);
	public Map<String, Object> getSoltByAgentId(String hotelId);
	public Map<String, Object> getDeviceCountByHotelId(String hotelId);
	public Map<String, Object> getAgentByInn(String hotelId);
	public Map<String, Object> getAgentAndRoomRelations(String agentId);
	public Map<String, Object> getslotIdByAgentId(String agentId);
	public void binding(Integer roomId,Integer slotId);
	public void saveFile(CommonsMultipartFile commonsMultipartFile);
}
