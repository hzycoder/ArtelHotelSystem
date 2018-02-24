package com.hotel.services;

import java.util.Map;
import com.login.dto.UserDto;

public interface HotelService {
	public  Map<String, Object> getHotels(UserDto user);

}
