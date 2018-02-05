package com.common.pojo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * SysHotelUser entity. @author MyEclipse Persistence Tools
 */
@Component("hotelUser")
public class SysHotelUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer hotelId;

	// Constructors

	/** default constructor */
	public SysHotelUser() {
	}

	/** minimal constructor */
	public SysHotelUser(Integer id) {
		this.id = id;
	}

	/** full constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public SysHotelUser(Integer id, Integer userId, Integer hotelId) {
		super();
		this.id = id;
		this.userId = userId;
		this.hotelId = hotelId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}


}