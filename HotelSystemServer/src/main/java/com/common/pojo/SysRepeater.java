package com.common.pojo;

/**
 * SysRepeater entity. @author MyEclipse Persistence Tools
 */

public class SysRepeater implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysRoom sysRoom;
	private SysHotel sysHotel;
	private String repeaterCode;

	// Constructors

	/** default constructor */
	public SysRepeater() {
	}

	/** minimal constructor */
	public SysRepeater(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysRepeater(Integer id, SysRoom sysRoom, SysHotel sysHotel, String repeaterCode) {
		this.id = id;
		this.sysRoom = sysRoom;
		this.sysHotel = sysHotel;
		this.repeaterCode = repeaterCode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysRoom getSysRoom() {
		return this.sysRoom;
	}

	public void setSysRoom(SysRoom sysRoom) {
		this.sysRoom = sysRoom;
	}

	public SysHotel getSysHotel() {
		return this.sysHotel;
	}

	public void setSysHotel(SysHotel sysHotel) {
		this.sysHotel = sysHotel;
	}

	public String getRepeaterCode() {
		return this.repeaterCode;
	}

	public void setRepeaterCode(String repeaterCode) {
		this.repeaterCode = repeaterCode;
	}

}