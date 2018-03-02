package com.common.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AgentList entity. @author MyEclipse Persistence Tools
 */

public class AgentList implements java.io.Serializable {

	// Fields

	private Integer idAgentList;
	private String macAddress;
	private Timestamp createTime;
	private Integer deviceCount;

	// Constructors
	public AgentList() {
		super();
	}

	

	public AgentList(Integer idAgentList, String macAddress, Timestamp createTime, Integer deviceCount) {
		super();
		this.idAgentList = idAgentList;
		this.macAddress = macAddress;
		this.createTime = createTime;
		this.deviceCount = deviceCount;
	}



	// Property accessors

	public Integer getIdAgentList() {
		return this.idAgentList;
	}

	public void setIdAgentList(Integer idAgentList) {
		this.idAgentList = idAgentList;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getDeviceCount() {
		return this.deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	

}