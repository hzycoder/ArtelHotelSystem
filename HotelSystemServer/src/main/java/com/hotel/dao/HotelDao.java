package com.hotel.dao;

import java.util.List;
import java.util.Map;

import com.common.pojo.HotelList;
import com.common.pojo.RoomList;
import com.hotel.dto.HotelDto;
import com.login.dto.UserDto;

public interface HotelDao {
	public List<HotelList> gethotels();
	public Integer getHotelsCount();
	public int delHotel(String HotelID);
	public void addHotel(HotelList hotelList);
	public List<RoomList> getRooms(String hotelId);
	public Integer getRoomsCount(String hotelId);
}
