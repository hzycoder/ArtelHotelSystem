package com.hotel.services;

import java.util.Map;

import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.login.dto.UserDto;

public interface HotelService {
	public Map<String, Object> getHotels();
	public Map<String, Object> delHotel(String hotelID);
	public void addHotel(HotelDto hotelDto);
	public Map<String,Object> getRooms(String hotelId);
	public Map<String, Object> delRoom(String roomID);
	public void addRoom(RoomDto roomDto);
	public Map<String, Object> getHotelsByConditions(String hotelName,String hotelAddress);
}
