package com.common.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * RoomList entity. @author MyEclipse Persistence Tools
 */

public class RoomList implements java.io.Serializable {

	// Fields

	private Integer idRoomList;
	private HotelList hotelList;
	private String roomId;
	private String roomNum;
	private String soltNum;
	private Short floor;
	private Set roomSoltLists = new HashSet(0);

	// Constructors

	/** default constructor */
	public RoomList() {
	}

	/** minimal constructor */
	public RoomList(HotelList hotelList, String roomId, String roomNum, String soltNum, Short floor) {
		this.hotelList = hotelList;
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.soltNum = soltNum;
		this.floor = floor;
	}

	/** full constructor */
	public RoomList(HotelList hotelList, String roomId, String roomNum, String soltNum, Short floor,
			Set roomSoltLists) {
		this.hotelList = hotelList;
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.soltNum = soltNum;
		this.floor = floor;
		this.roomSoltLists = roomSoltLists;
	}

	// Property accessors

	public Integer getIdRoomList() {
		return this.idRoomList;
	}

	public void setIdRoomList(Integer idRoomList) {
		this.idRoomList = idRoomList;
	}

	public HotelList getHotelList() {
		return this.hotelList;
	}

	public void setHotelList(HotelList hotelList) {
		this.hotelList = hotelList;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getSoltNum() {
		return this.soltNum;
	}

	public void setSoltNum(String soltNum) {
		this.soltNum = soltNum;
	}

	public Short getFloor() {
		return this.floor;
	}

	public void setFloor(Short floor) {
		this.floor = floor;
	}

	public Set getRoomSoltLists() {
		return this.roomSoltLists;
	}

	public void setRoomSoltLists(Set roomSoltLists) {
		this.roomSoltLists = roomSoltLists;
	}

}