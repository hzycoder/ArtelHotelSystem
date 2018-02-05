package com.common.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * SysHotel entity. @author MyEclipse Persistence Tools
 */
@Component("hotel")
public class SysHotel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer hotelManager;
	private String hotelCode;
	private String hotelName;
	private String hotelProvince;
	private String hotelCity;
	private String hotelAddress;
	private String hotelPhone;
	private Date createTime;
//	private Set sysHotelUsers = new HashSet(0);
//	private Set sysRooms = new HashSet(0);
//	private Set dataRoomOperations = new HashSet(0);
//	private Set sysRepeaters = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysHotel() {
	}

	/** minimal constructor */
	public SysHotel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	

	// Property accessors

	
	public Integer getId() {
		return this.id;
	}


	public SysHotel(Integer id, Integer hotelManager, String hotelCode, String hotelName, String hotelProvince,
			String hotelCity, String hotelAddress, String hotelPhone, Date createTime) {
		super();
		this.id = id;
		this.hotelManager = hotelManager;
		this.hotelCode = hotelCode;
		this.hotelName = hotelName;
		this.hotelProvince = hotelProvince;
		this.hotelCity = hotelCity;
		this.hotelAddress = hotelAddress;
		this.hotelPhone = hotelPhone;
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(Integer hotelManager) {
		this.hotelManager = hotelManager;
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

}