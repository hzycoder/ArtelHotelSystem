package com.device.dto;

public class BindingRoomDto {
	private String roomCode;
	private String roomNum;
	private String floor;

	public BindingRoomDto() {
		super();
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
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

	@Override
	public String toString() {
		return "BindingRoomDto [roomCode=" + roomCode + ", roomNum=" + roomNum + ", floor=" + floor + "]";
	}

}
