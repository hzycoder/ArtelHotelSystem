package com.common.pojo;

/**
 * UserHotelList entity. @author MyEclipse Persistence Tools
 */

public class UserHotelList implements java.io.Serializable {

	// Fields

	private Integer idUserHotelList;
	private HotelList hotelList;
	private LoginUserList loginUserList;

	// Constructors

	/** default constructor */
	public UserHotelList() {
	}

	/** full constructor */
	public UserHotelList(HotelList hotelList, LoginUserList loginUserList) {
		this.hotelList = hotelList;
		this.loginUserList = loginUserList;
	}

	// Property accessors

	public Integer getIdUserHotelList() {
		return this.idUserHotelList;
	}

	public void setIdUserHotelList(Integer idUserHotelList) {
		this.idUserHotelList = idUserHotelList;
	}

	public HotelList getHotelList() {
		return this.hotelList;
	}

	public void setHotelList(HotelList hotelList) {
		this.hotelList = hotelList;
	}

	public LoginUserList getLoginUserList() {
		return this.loginUserList;
	}

	public void setLoginUserList(LoginUserList loginUserList) {
		this.loginUserList = loginUserList;
	}

}