package com.hotel.dao;

import java.util.List;
import java.util.Map;

import com.common.pojo.HotelList;
import com.common.pojo.RoomList;
import com.hotel.dto.HotelDto;
import com.user.dto.UserDto;

public interface HotelDao {
	public List<HotelList> gethotels(String userID);
	public List<HotelList> gethotels();
//	public Integer getHotelsCount(String sql);
	public int delHotel(String hotelID);
	public void addHotel(HotelList hotelList);
	public List<RoomList> getRooms(String hotelId);
	public Integer getRoomsCount(String hotelId);
	public int delRoom(String roomID);
	public void addRoom(RoomList roomList);
	public Integer getRoomCountByHotelID(int hotelID);
	public List<HotelList> gethotelsByConditions(String hotelName,String hotelAddress,String userID);
	public List<HotelList> gethotelsByConditions(String hotelName,String hotelAddress);
	public List<String> getTypeOfHotel(String hotelId);
	public Integer getMaxHotelId();
}
