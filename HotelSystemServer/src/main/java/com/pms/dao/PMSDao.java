package com.pms.dao;

import java.util.List;

import com.pms.dto.HotelDto;
import com.pms.dto.RoomStatusDto;

public interface PMSDao {
	public List<HotelDto> getHotelList(Integer pmsId);
	public List<RoomStatusDto> getRommStatusListByHotelCode(String hotelCode);
	public RoomStatusDto getRoomStatusByRoomCode(String roomCode);
}
