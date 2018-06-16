package com.device.dto;

import java.sql.Timestamp;

public class BindingAgentDto {
	private Integer slotId;
	private Integer subNetNum;
	private Integer agentId;
	private String macAddress;
	private Timestamp agentCreateTime;
	private Integer deviceCount;

	public BindingAgentDto() {
		super();
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getSubNetNum() {
		return subNetNum;
	}

	public void setSubNetNum(Integer subNetNum) {
		this.subNetNum = subNetNum;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Timestamp getAgentCreateTime() {
		return agentCreateTime;
	}

	public void setAgentCreateTime(Timestamp agentCreateTime) {
		this.agentCreateTime = agentCreateTime;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	@Override
	public String toString() {
		return "bindingAgentIdDto [slotId=" + slotId + ", subNetNum=" + subNetNum + ", agentId=" + agentId
				+ ", macAddress=" + macAddress + ", agentCreateTime=" + agentCreateTime + ", deviceCount=" + deviceCount
				+ "]";
	}

}
