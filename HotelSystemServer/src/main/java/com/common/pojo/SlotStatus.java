package com.common.pojo;

/**
 * SlotStatus entity. @author MyEclipse Persistence Tools
 */

public class SlotStatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer idslotList;
	private Boolean slotStatus;
	private String slotCardNum;
	private Boolean isSlotIllegal;
	private String lockStatus;
	private String isChildDeviceRegister;
	private String isChildDeviceOnline;
	private String isServiceLightOn;
	private Boolean isRoomLightOn;

	// Constructors

	/** default constructor */
	public SlotStatus() {
	}

	/** full constructor */
	public SlotStatus(Integer idslotList, Boolean slotStatus, Boolean isSlotIllegal, String lockStatus,
			String isChildDeviceRegister, String isChildDeviceOnline, String isServiceLightOn, Boolean isRoomLightOn) {
		this.idslotList = idslotList;
		this.slotStatus = slotStatus;
		this.isSlotIllegal = isSlotIllegal;
		this.lockStatus = lockStatus;
		this.isChildDeviceRegister = isChildDeviceRegister;
		this.isChildDeviceOnline = isChildDeviceOnline;
		this.isServiceLightOn = isServiceLightOn;
		this.isRoomLightOn = isRoomLightOn;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdslotList() {
		return this.idslotList;
	}

	public void setIdslotList(Integer idslotList) {
		this.idslotList = idslotList;
	}

	public Boolean getSlotStatus() {
		return this.slotStatus;
	}

	public void setSlotStatus(Boolean slotStatus) {
		this.slotStatus = slotStatus;
	}

	public Boolean getIsSlotIllegal() {
		return this.isSlotIllegal;
	}

	public void setIsSlotIllegal(Boolean isSlotIllegal) {
		this.isSlotIllegal = isSlotIllegal;
	}

	public String getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getIsChildDeviceRegister() {
		return this.isChildDeviceRegister;
	}

	public void setIsChildDeviceRegister(String isChildDeviceRegister) {
		this.isChildDeviceRegister = isChildDeviceRegister;
	}

	public String getIsChildDeviceOnline() {
		return this.isChildDeviceOnline;
	}

	public void setIsChildDeviceOnline(String isChildDeviceOnline) {
		this.isChildDeviceOnline = isChildDeviceOnline;
	}

	public String getIsServiceLightOn() {
		return isServiceLightOn;
	}

	public void setIsServiceLightOn(String isServiceLightOn) {
		this.isServiceLightOn = isServiceLightOn;
	}

	public Boolean getIsRoomLightOn() {
		return this.isRoomLightOn;
	}

	public void setIsRoomLightOn(Boolean isRoomLightOn) {
		this.isRoomLightOn = isRoomLightOn;
	}

	public String getSlotCardNum() {
		return slotCardNum;
	}

	public void setSlotCardNum(String slotCardNum) {
		this.slotCardNum = slotCardNum;
	}

}