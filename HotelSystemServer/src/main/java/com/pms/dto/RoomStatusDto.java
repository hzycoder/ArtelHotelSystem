package com.pms.dto;

public class RoomStatusDto {
	private String hotelCode;
	private String roomCode;
	private String roomNum;
	private String roomFloor;
	private boolean slotStatus;
	private String cardNum;
	private boolean isSlotIllegal;
	private String lockStatus;
	private String isChildDeviceRegister;
	private String isChildDeviceOnline;
	private String isServiceLightOn;
	private Boolean isRoomLightOn;

	public RoomStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
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

	public String getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}

	public boolean isSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(boolean slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getIsChildDeviceRegister() {
		return isChildDeviceRegister;
	}

	public void setIsChildDeviceRegister(String isChildDeviceRegister) {
		this.isChildDeviceRegister = isChildDeviceRegister;
	}

	public String getIsChildDeviceOnline() {
		return isChildDeviceOnline;
	}

	public void setIsChildDeviceOnline(String isChildDeviceOnline) {
		this.isChildDeviceOnline = isChildDeviceOnline;
	}

	public boolean isSlotIllegal() {
		return isSlotIllegal;
	}

	public void setSlotIllegal(boolean isSlotIllegal) {
		this.isSlotIllegal = isSlotIllegal;
	}

	public String getIsServiceLightOn() {
		return isServiceLightOn;
	}

	public void setIsServiceLightOn(String isServiceLightOn) {
		this.isServiceLightOn = isServiceLightOn;
	}

	public Boolean getIsRoomLightOn() {
		return isRoomLightOn;
	}

	public void setIsRoomLightOn(Boolean isRoomLightOn) {
		this.isRoomLightOn = isRoomLightOn;
	}

}
