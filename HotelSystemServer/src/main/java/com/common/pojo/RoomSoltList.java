package com.common.pojo;

/**
 * RoomSoltList entity. @author MyEclipse Persistence Tools
 */

public class RoomSoltList implements java.io.Serializable {

	// Fields

	private Integer idRoomSlotList;
	private RoomList roomList;
	private SoltList soltList;

	// Constructors

	/** default constructor */
	public RoomSoltList() {
	}

	/** full constructor */
	public RoomSoltList(RoomList roomList, SoltList soltList) {
		this.roomList = roomList;
		this.soltList = soltList;
	}

	// Property accessors

	public Integer getIdRoomSlotList() {
		return this.idRoomSlotList;
	}

	public void setIdRoomSlotList(Integer idRoomSlotList) {
		this.idRoomSlotList = idRoomSlotList;
	}

	public RoomList getRoomList() {
		return this.roomList;
	}

	public void setRoomList(RoomList roomList) {
		this.roomList = roomList;
	}

	public SoltList getSoltList() {
		return this.soltList;
	}

	public void setSoltList(SoltList soltList) {
		this.soltList = soltList;
	}

}