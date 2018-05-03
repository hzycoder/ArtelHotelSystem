package com.pms.services;

import java.util.Map;


public interface PMSServices {
	public Map<String,Object> getHotelList(Integer pmsId);
	public Map<String, Object> getRommStatusListByHotelCode(String hotelCode);
	public Map<String, Object> getRoomStatusByRoomCode(String roomCode);
}
