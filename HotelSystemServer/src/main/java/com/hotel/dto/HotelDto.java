package com.hotel.dto;

import java.sql.Timestamp;

public class HotelDto {
	private Integer idHotelList;
	private String hotelId;
	private String hotelName;
	private String country;
	private String province;
	private String city;
	private String address;
	private String hotelPhone;
	private String hotelManager;
	private String repeaterCount;
	private String deviceCount;
	private String status;
	private Timestamp createTime;
	private Integer idUserList;
	private Integer pmsId;

	private String account;
	private String userName;
	private String userPhone;
	private Integer permission;

	public HotelDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdHotelList() {
		return idHotelList;
	}

	public void setIdHotelList(Integer idHotelList) {
		this.idHotelList = idHotelList;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(String hotelManager) {
		this.hotelManager = hotelManager;
	}

	public String getRepeaterCount() {
		return repeaterCount;
	}

	public void setRepeaterCount(String repeaterCount) {
		this.repeaterCount = repeaterCount;
	}

	public String getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(String deviceCount) {
		this.deviceCount = deviceCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIdUserList() {
		return idUserList;
	}

	public void setIdUserList(Integer idUserList) {
		this.idUserList = idUserList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getPmsId() {
		return pmsId;
	}

	public void setPmsId(Integer pmsId) {
		this.pmsId = pmsId;
	}

	@Override
	public String toString() {
		return "HotelDto [idHotelList=" + idHotelList + ", hotelId=" + hotelId + ", hotelName=" + hotelName
				+ ", country=" + country + ", province=" + province + ", city=" + city + ", address=" + address
				+ ", hotelPhone=" + hotelPhone + ", hotelManager=" + hotelManager + ", repeaterCount=" + repeaterCount
				+ ", deviceCount=" + deviceCount + ", status=" + status + ", createTime=" + createTime + ", idUserList="
				+ idUserList + ", pmsId=" + pmsId + ", account=" + account + ", userName=" + userName + ", userPhone="
				+ userPhone + ", permission=" + permission + "]";
	}

}
