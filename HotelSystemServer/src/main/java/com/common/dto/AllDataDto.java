package com.common.dto;

import java.sql.Timestamp;

public class AllDataDto {
	private String hotelName;
	private String hotelCode;
	private String hotelAddress;
	private Timestamp hotelCreateTime;
	private String hotelPhone;
	private String hotelStatus;
	private String userName;
	private String userPhone;
	private String userAccount;
	private String pmsName;
	private Integer roomCount;
	private Integer deviceCount;

	public AllDataDto() {
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public Timestamp getHotelCreateTime() {
		return hotelCreateTime;
	}

	public void setHotelCreateTime(Timestamp hotelCreateTime) {
		this.hotelCreateTime = hotelCreateTime;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getHotelStatus() {
		return hotelStatus;
	}

	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPmsName() {
		return pmsName;
	}

	public void setPmsName(String pmsName) {
		this.pmsName = pmsName;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

}
