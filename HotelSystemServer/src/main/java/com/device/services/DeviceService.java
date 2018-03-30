package com.device.services;

import java.util.Map;

public interface DeviceService {
	public Map<String, Object> getAgentByHotelId(String hotelId);
	public Map<String, Object> getSoltByAgentId(String hotelId);
	public Map<String, Object> getDeviceCountByHotelId(String hotelId);
	public Map<String, Object> getAgentByInn(String hotelId);
}
