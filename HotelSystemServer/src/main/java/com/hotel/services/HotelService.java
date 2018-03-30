package com.hotel.services;

import java.util.Map;

import com.hotel.dto.HotelDto;
import com.hotel.dto.RoomDto;
import com.user.dto.UserDto;

public interface HotelService {
	public Map<String, Object> getHotels(String userID, String permission);

	public Map<String, Object> delHotel(String hotelID);

	public void addHotel(HotelDto hotelDto);

	public Map<String, Object> getRooms(String hotelId);

	public Map<String, Object> delRoom(String roomID);

	public void addRoom(RoomDto roomDto);

	public void batchAddRoom(RoomDto roomDto);

	public Map<String, Object> getHotelsByConditions(String hotelName, String hotelAddress, String userID,
			String permission);

	public Map<String, Object> getTypeOfHotel(String hotelId);
}
