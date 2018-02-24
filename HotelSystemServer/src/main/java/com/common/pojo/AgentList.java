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
	private Set soltConfigures = new HashSet(0);
	private Set hotelAgentLists = new HashSet(0);
	private Set soltLists = new HashSet(0);

	// Constructors

	/** default constructor */
	public AgentList() {
	}

	/** minimal constructor */
	public AgentList(String macAddress, Timestamp createTime, Integer deviceCount) {
		this.macAddress = macAddress;
		this.createTime = createTime;
		this.deviceCount = deviceCount;
	}

	/** full constructor */
	public AgentList(String macAddress, Timestamp createTime, Integer deviceCount, Set soltConfigures,
			Set hotelAgentLists, Set soltLists) {
		this.macAddress = macAddress;
		this.createTime = createTime;
		this.deviceCount = deviceCount;
		this.soltConfigures = soltConfigures;
		this.hotelAgentLists = hotelAgentLists;
		this.soltLists = soltLists;
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

	public Set getSoltConfigures() {
		return this.soltConfigures;
	}

	public void setSoltConfigures(Set soltConfigures) {
		this.soltConfigures = soltConfigures;
	}

	public Set getHotelAgentLists() {
		return this.hotelAgentLists;
	}

	public void setHotelAgentLists(Set hotelAgentLists) {
		this.hotelAgentLists = hotelAgentLists;
	}

	public Set getSoltLists() {
		return this.soltLists;
	}

	public void setSoltLists(Set soltLists) {
		this.soltLists = soltLists;
	}

}