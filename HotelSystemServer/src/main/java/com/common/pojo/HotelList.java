package com.common.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * HotelList entity. @author MyEclipse Persistence Tools
 */

public class HotelList implements java.io.Serializable {

	// Fields

	private Integer idHotelList;
	private String hotelId;
	private String hotelName;
	private String country;
	private String province;
	private String city;
	private String address;
	private String hotelPhone;
	private String hotelManager;
	private Timestamp createTime;
	private String repeaterCount;
	private String deviceCount;
	private String status;
	// private Set hotelAgentLists = new HashSet(0);
	// private Set roomLists = new HashSet(0);
	// private Set userHotelLists = new HashSet(0);

	// Constructors

	/** default constructor */
	public HotelList() {
	}

	/** minimal constructor */
	public HotelList(String hotelId, String hotelName, String country, String province, String city, String address,
			Timestamp createTime) {
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.country = country;
		this.province = province;
		this.city = city;
		this.address = address;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getIdHotelList() {
		return this.idHotelList;
	}

	public void setIdHotelList(Integer idHotelList) {
		this.idHotelList = idHotelList;
	}

	public String getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotelPhone() {
		return this.hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getHotelManager() {
		return this.hotelManager;
	}

	public void setHotelManager(String hotelManager) {
		this.hotelManager = hotelManager;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	@Override
	public String toString() {
		return "HotelList [idHotelList=" + idHotelList + ", hotelId=" + hotelId + ", hotelName=" + hotelName
				+ ", country=" + country + ", province=" + province + ", city=" + city + ", address=" + address
				+ ", hotelPhone=" + hotelPhone + ", hotelManager=" + hotelManager + ", createTime=" + createTime
				+ ", repeaterCount=" + repeaterCount + ", deviceCount=" + deviceCount + ", status=" + status + "]";
	}

	// public Set getHotelAgentLists() {
	// return this.hotelAgentLists;
	// }
	//
	// public void setHotelAgentLists(Set hotelAgentLists) {
	// this.hotelAgentLists = hotelAgentLists;
	// }
	//
	// public Set getRoomLists() {
	// return this.roomLists;
	// }
	//
	// public void setRoomLists(Set roomLists) {
	// this.roomLists = roomLists;
	// }
	//
	// public Set getUserHotelLists() {
	// return this.userHotelLists;
	// }
	//
	// public void setUserHotelLists(Set userHotelLists) {
	// this.userHotelLists = userHotelLists;
	// }

}