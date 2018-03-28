package com.device.dto;

public class DeviceCountDto {
	String hotelId;
	int deviceCount;
	int agentCount;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public int getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(int agentCount) {
		this.agentCount = agentCount;
	}

	public DeviceCountDto() {
		super();
	}

	public DeviceCountDto(String hotelId, int deviceCount, int agentCount) {
		super();
		this.hotelId = hotelId;
		this.deviceCount = deviceCount;
		this.agentCount = agentCount;
	}

}
