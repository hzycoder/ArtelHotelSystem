package com.common.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * RoomList entity. @author MyEclipse Persistence Tools
 */

public class RoomList implements java.io.Serializable {

	// Fields

	private Integer idRoomList;
	private String roomId;
	private String roomNum;
	private String soltNum;
	private String floor;
	private Integer hotelId;
//	private Set roomSoltLists = new HashSet(0);

	// Constructors

	/** default constructor */
	public RoomList() {
	}

	/** minimal constructor */
	public RoomList(String roomId, String roomNum, String soltNum, String floor) {
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.soltNum = soltNum;
		this.floor = floor;
	}

//	/** full constructor */
//	public RoomList(HotelList hotelList, String roomId, String roomNum, String soltNum, Short floor,
//			Set roomSoltLists) {
//		this.hotelList = hotelList;
//		this.roomId = roomId;
//		this.roomNum = roomNum;
//		this.soltNum = soltNum;
//		this.floor = floor;
//		this.roomSoltLists = roomSoltLists;
//	}

	// Property accessors

	public Integer getIdRoomList() {
		return this.idRoomList;
	}

	public void setIdRoomList(Integer idRoomList) {
		this.idRoomList = idRoomList;
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

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "RoomList [idRoomList=" + idRoomList + ", roomId=" + roomId + ", roomNum=" + roomNum + ", soltNum="
				+ soltNum + ", floor=" + floor + ", hotelId=" + hotelId + "]";
	}
	
	
	
//
//	public Set getRoomSoltLists() {
//		return this.roomSoltLists;
//	}
//
//	public void setRoomSoltLists(Set roomSoltLists) {
//		this.roomSoltLists = roomSoltLists;
//	}

}