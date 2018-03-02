package com.hotel.services;

import java.util.Map;

import com.hotel.dto.HotelDto;
import com.login.dto.UserDto;

public interface HotelService {
	public Map<String, Object> getHotels();
	public Map<String, Object> delHotel(String hotelID);
	public void addHotel(HotelDto hotelDto);
	public Map<String,Object> getRooms(String hotelId);
}
