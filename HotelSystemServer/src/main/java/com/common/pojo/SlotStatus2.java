package com.common.pojo;

import java.sql.Timestamp;

/**
 * SlotStatus entity. @author MyEclipse Persistence Tools
 */

public class SlotStatus2 implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer slotID;
	private Boolean slotStatus;
	private String slotCardNum;
	private Boolean powerStatus;
	private String serviceLight;
	private Boolean isDevOnline;
	private Timestamp time;

	public SlotStatus2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SlotStatus2(Integer id, Integer slotID, Boolean slotStatus, String slotCardNum, Boolean powerStatus,
			String serviceLight, Boolean isDevOnline, Timestamp time) {
		super();
		this.id = id;
		this.slotID = slotID;
		this.slotStatus = slotStatus;
		this.slotCardNum = slotCardNum;
		this.powerStatus = powerStatus;
		this.serviceLight = serviceLight;
		this.isDevOnline = isDevOnline;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSlotID() {
		return slotID;
	}

	public void setSlotID(Integer slotID) {
		this.slotID = slotID;
	}

	public Boolean getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(Boolean slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getSlotCardNum() {
		return slotCardNum;
	}

	public void setSlotCardNum(String slotCardNum) {
		this.slotCardNum = slotCardNum;
	}

	public Boolean getPowerStatus() {
		return powerStatus;
	}

	public void setPowerStatus(Boolean powerStatus) {
		this.powerStatus = powerStatus;
	}

	public String getServiceLight() {
		return serviceLight;
	}

	public void setServiceLight(String serviceLight) {
		this.serviceLight = serviceLight;
	}

	public Boolean getIsDevOnline() {
		return isDevOnline;
	}

	public void setIsDevOnline(Boolean isDevOnline) {
		this.isDevOnline = isDevOnline;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}