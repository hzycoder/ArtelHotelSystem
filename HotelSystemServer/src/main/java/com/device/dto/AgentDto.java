package com.device.dto;

import java.sql.Timestamp;

/**
 * 获取已经绑定房间的中继数据
 * 
 * @author huangzhenyang
 *
 */
public class AgentDto {
	private Integer idAgentList;
	private String macAddress;
	private Timestamp createTime;
	private Integer deviceCount;
	private Integer idRoomSlotList;
	private Integer idRoomList;
	private Integer idsoltList;
	private String roomId;
	private String roomNum;
	private String floor;
	private Integer hotelId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getFloor() {
		return floor;
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

	public Integer getIdAgentList() {
		return idAgentList;
	}

	public void setIdAgentList(Integer idAgentList) {
		this.idAgentList = idAgentList;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	public Integer getIdsoltList() {
		return idsoltList;
	}

	public void setIdsoltList(Integer idsoltList) {
		this.idsoltList = idsoltList;
	}

	public Integer getIdRoomSlotList() {
		return idRoomSlotList;
	}

	public void setIdRoomSlotList(Integer idRoomSlotList) {
		this.idRoomSlotList = idRoomSlotList;
	}

	public Integer getIdRoomList() {
		return idRoomList;
	}

	public void setIdRoomList(Integer idRoomList) {
		this.idRoomList = idRoomList;
	}

	public AgentDto() {
		super();
	}

}
