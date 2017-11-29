package com.common.pojo;

/**
 * SysHotelUser entity. @author MyEclipse Persistence Tools
 */

public class SysHotelUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUser;
	private SysHotel sysHotel;

	// Constructors

	/** default constructor */
	public SysHotelUser() {
	}

	/** minimal constructor */
	public SysHotelUser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysHotelUser(Integer id, SysUser sysUser, SysHotel sysHotel) {
		this.id = id;
		this.sysUser = sysUser;
		this.sysHotel = sysHotel;
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

	public SysHotel getSysHotel() {
		return this.sysHotel;
	}

	public void setSysHotel(SysHotel sysHotel) {
		this.sysHotel = sysHotel;
	}

}