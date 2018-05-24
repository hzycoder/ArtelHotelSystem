package com.tcp.dto;

import java.util.List;

public class UpgradeDto {
	private List<String> macAddress;
	private String hotelName;

	public UpgradeDto() {
		super();
	}

	public List<String> getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(List<String> macAddress) {
		this.macAddress = macAddress;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Override
	public String toString() {
		return "upgradeDto [macAddress=" + macAddress + ", hotelName=" + hotelName + "]";
	}

}
