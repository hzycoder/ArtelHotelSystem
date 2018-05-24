package com.tcp.dao;

import java.util.List;

import com.common.pojo.HotelList;
import com.tcp.dto.UpgradeDto;

public interface TcpDao {
	public List<UpgradeDto> getUpgrade(String hotelId);
	public List<String> getMacAddress(String hotelId);
	public HotelList getHotel(String hotelId);
}
