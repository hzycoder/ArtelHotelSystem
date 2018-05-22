package com.hotel.dao;

import java.util.List;

import com.common.pojo.HotelList;
import com.common.pojo.RoomList;
import com.hotel.dto.HotelDto;

public interface HotelDao {
	public List<HotelDto> gethotels(String userID);
	public List<HotelDto> gethotels();
//	public Integer getHotelsCount(String sql);
	public int delHotel(String hotelID);
	public void addHotel(HotelList hotelList);
	public List<RoomList> getRooms(String hotelId);
	public List<RoomList> getUnbindedRooms(String hotelId);
	public Integer getRoomsCount(String hotelId);
	public int delRoom(String roomID);
	public void addRoom(RoomList roomList);
	public Integer getRoomCountByHotelID(int hotelID);
	public List<HotelList> gethotelsByConditions(String hotelName,String hotelAddress,String userID);
	public List<HotelList> gethotelsByConditions(String hotelName,String hotelAddress);
	public List<String> getTypeOfHotel(String hotelId);
	public Integer getMaxHotelId();
	public List<HotelList> getHotelByHotelName(String hotelName);
	public List<RoomList> verifyRoomNum (String roomNum,Integer hotelId);
	
}
