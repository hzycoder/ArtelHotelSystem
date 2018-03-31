package com.common.pojo;

/**
 * RoomSoltList entity. @author MyEclipse Persistence Tools
 */

public class RoomSoltList implements java.io.Serializable {

	// Fields

	private Integer idRoomSlotList;
	private Integer idRoomList;
	private Integer idsoltList;

	// Constructors

	/** default constructor */
	public RoomSoltList() {
	}

	public RoomSoltList(Integer idRoomSlotList, Integer idRoomList, Integer idsoltList) {
		super();
		this.idRoomSlotList = idRoomSlotList;
		this.idRoomList = idRoomList;
		this.idsoltList = idsoltList;
	}

	public Integer getIdRoomSlotList() {
		return this.idRoomSlotList;
	}

	public Integer getIdRoomList() {
		return idRoomList;
	}

	public void setIdRoomList(Integer idRoomList) {
		this.idRoomList = idRoomList;
	}

	public Integer getIdsoltList() {
		return idsoltList;
	}

	public void setIdsoltList(Integer idsoltList) {
		this.idsoltList = idsoltList;
	}

	public void setIdRoomSlotList(Integer idRoomSlotList) {
		this.idRoomSlotList = idRoomSlotList;
	}

}