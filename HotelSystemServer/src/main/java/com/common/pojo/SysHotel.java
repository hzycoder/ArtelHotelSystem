package com.common.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysHotel entity. @author MyEclipse Persistence Tools
 */

public class SysHotel implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUser;
	private String hotelCode;
	private String hotelName;
	private String hotelProvince;
	private String hotelCity;
	private String hotelAddress;
	private String hotelPhone;
	private Date createTime;
	private Set sysHotelUsers = new HashSet(0);
	private Set sysRooms = new HashSet(0);
	private Set dataRoomOperations = new HashSet(0);
	private Set sysRepeaters = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysHotel() {
	}

	/** minimal constructor */
	public SysHotel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysHotel(Integer id, SysUser sysUser, String hotelCode, String hotelName, String hotelProvince,
			String hotelCity, String hotelAddress, String hotelPhone, Date createTime, Set sysHotelUsers, Set sysRooms,
			Set dataRoomOperations, Set sysRepeaters) {
		this.id = id;
		this.sysUser = sysUser;
		this.hotelCode = hotelCode;
		this.hotelName = hotelName;
		this.hotelProvince = hotelProvince;
		this.hotelCity = hotelCity;
		this.hotelAddress = hotelAddress;
		this.hotelPhone = hotelPhone;
		this.createTime = createTime;
		this.sysHotelUsers = sysHotelUsers;
		this.sysRooms = sysRooms;
		this.dataRoomOperations = dataRoomOperations;
		this.sysRepeaters = sysRepeaters;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getHotelCode() {
		return this.hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelProvince() {
		return this.hotelProvince;
	}

	public void setHotelProvince(String hotelProvince) {
		this.hotelProvince = hotelProvince;
	}

	public String getHotelCity() {
		return this.hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelAddress() {
		return this.hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelPhone() {
		return this.hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set getSysHotelUsers() {
		return this.sysHotelUsers;
	}

	public void setSysHotelUsers(Set sysHotelUsers) {
		this.sysHotelUsers = sysHotelUsers;
	}

	public Set getSysRooms() {
		return this.sysRooms;
	}

	public void setSysRooms(Set sysRooms) {
		this.sysRooms = sysRooms;
	}

	public Set getDataRoomOperations() {
		return this.dataRoomOperations;
	}

	public void setDataRoomOperations(Set dataRoomOperations) {
		this.dataRoomOperations = dataRoomOperations;
	}

	public Set getSysRepeaters() {
		return this.sysRepeaters;
	}

	public void setSysRepeaters(Set sysRepeaters) {
		this.sysRepeaters = sysRepeaters;
	}

}