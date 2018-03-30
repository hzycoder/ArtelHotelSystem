package com.hotel.dto;

import java.util.List;

public class RoomDto {
	private String idRoomList;
	private String roomId;
	private String roomNum;
	private List<String> roomNumList;
	private String floor;
	private Integer hotelId;
	private String hotelNum;

	public RoomDto() {
	}

	public List<String> getRoomNumList() {
		return roomNumList;
	}

	public void setRoomNumList(List<String> roomNumList) {
		this.roomNumList = roomNumList;
	}

	public String getIdRoomList() {
		return idRoomList;
	}

	public void setIdRoomList(String idRoomList) {
		this.idRoomList = idRoomList;
	}

	public String getHotelNum() {
		return hotelNum;
	}

	public void setHotelNum(String hotelNum) {
		this.hotelNum = hotelNum;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

}
