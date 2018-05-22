package com.common.dto;

import java.sql.Timestamp;

public class SlotStatusDto {
	private String roomNum;
	private String slotStatus;
	private String isSlotIllegal;
	private String lockStatus;
	private String isRoomLightOn;
	private String isDeviceOnline;
	private Timestamp time;

	public SlotStatusDto() {
		super();
	}

	public SlotStatusDto(String roomNum, String slotStatus, String isSlotIllegal, String lockStatus,
			String isRoomLightOn, String isDeviceOnline, Timestamp time) {
		super();
		this.roomNum = roomNum;
		this.slotStatus = slotStatus;
		this.isSlotIllegal = isSlotIllegal;
		this.lockStatus = lockStatus;
		this.isRoomLightOn = isRoomLightOn;
		this.isDeviceOnline = isDeviceOnline;
		this.time = time;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getIsSlotIllegal() {
		return isSlotIllegal;
	}

	public void setIsSlotIllegal(String isSlotIllegal) {
		this.isSlotIllegal = isSlotIllegal;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getIsRoomLightOn() {
		return isRoomLightOn;
	}

	public void setIsRoomLightOn(String isRoomLightOn) {
		this.isRoomLightOn = isRoomLightOn;
	}

	public String getIsDeviceOnline() {
		return isDeviceOnline;
	}

	public void setIsDeviceOnline(String isDeviceOnline) {
		this.isDeviceOnline = isDeviceOnline;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
