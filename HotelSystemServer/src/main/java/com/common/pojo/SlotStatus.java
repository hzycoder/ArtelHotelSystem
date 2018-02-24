package com.common.pojo;

/**
 * SlotStatus entity. @author MyEclipse Persistence Tools
 */

public class SlotStatus implements java.io.Serializable {

	// Fields

	private Integer idslotStatus;
	private SoltList soltList;
	private String slotStatus;
	private String dev1status;
	private String dev2status;
	private String dev3status;

	// Constructors

	/** default constructor */
	public SlotStatus() {
	}

	/** full constructor */
	public SlotStatus(SoltList soltList, String slotStatus, String dev1status, String dev2status, String dev3status) {
		this.soltList = soltList;
		this.slotStatus = slotStatus;
		this.dev1status = dev1status;
		this.dev2status = dev2status;
		this.dev3status = dev3status;
	}

	// Property accessors

	public Integer getIdslotStatus() {
		return this.idslotStatus;
	}

	public void setIdslotStatus(Integer idslotStatus) {
		this.idslotStatus = idslotStatus;
	}

	public SoltList getSoltList() {
		return this.soltList;
	}

	public void setSoltList(SoltList soltList) {
		this.soltList = soltList;
	}

	public String getSlotStatus() {
		return this.slotStatus;
	}

	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getDev1status() {
		return this.dev1status;
	}

	public void setDev1status(String dev1status) {
		this.dev1status = dev1status;
	}

	public String getDev2status() {
		return this.dev2status;
	}

	public void setDev2status(String dev2status) {
		this.dev2status = dev2status;
	}

	public String getDev3status() {
		return this.dev3status;
	}

	public void setDev3status(String dev3status) {
		this.dev3status = dev3status;
	}

}