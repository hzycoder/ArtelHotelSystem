package com.common.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRoom entity. @author MyEclipse Persistence Tools
 */

public class SysRoom implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysHotel sysHotel;
	private String roomCode;
	private String roomSoltCode;
	private Short roomSoltStatus;
	private String roomFloor;
	private Short roomStatus;
	private Set dataRoomOperations = new HashSet(0);
	private Set sysRepeaters = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysRoom() {
	}

	/** minimal constructor */
	public SysRoom(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SysRoom(Integer id, SysHotel sysHotel, String roomCode, String roomSoltCode, Short roomSoltStatus,
			String roomFloor, Short roomStatus, Set dataRoomOperations, Set sysRepeaters) {
		this.id = id;
		this.sysHotel = sysHotel;
		this.roomCode = roomCode;
		this.roomSoltCode = roomSoltCode;
		this.roomSoltStatus = roomSoltStatus;
		this.roomFloor = roomFloor;
		this.roomStatus = roomStatus;
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

	public SysHotel getSysHotel() {
		return this.sysHotel;
	}

	public void setSysHotel(SysHotel sysHotel) {
		this.sysHotel = sysHotel;
	}

	public String getRoomCode() {
		return this.roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomSoltCode() {
		return this.roomSoltCode;
	}

	public void setRoomSoltCode(String roomSoltCode) {
		this.roomSoltCode = roomSoltCode;
	}

	public Short getRoomSoltStatus() {
		return this.roomSoltStatus;
	}

	public void setRoomSoltStatus(Short roomSoltStatus) {
		this.roomSoltStatus = roomSoltStatus;
	}

	public String getRoomFloor() {
		return this.roomFloor;
	}

	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}

	public Short getRoomStatus() {
		return this.roomStatus;
	}

	public void setRoomStatus(Short roomStatus) {
		this.roomStatus = roomStatus;
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