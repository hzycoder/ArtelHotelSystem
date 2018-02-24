package com.common.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SoltList entity. @author MyEclipse Persistence Tools
 */

public class SoltList implements java.io.Serializable {

	// Fields

	private Integer idsoltList;
	private AgentList agentList;
	private Integer subNetNum;
	private Set slotStatuses = new HashSet(0);
	private Set roomSoltLists = new HashSet(0);

	// Constructors

	/** default constructor */
	public SoltList() {
	}

	/** minimal constructor */
	public SoltList(AgentList agentList, Integer subNetNum) {
		this.agentList = agentList;
		this.subNetNum = subNetNum;
	}

	/** full constructor */
	public SoltList(AgentList agentList, Integer subNetNum, Set slotStatuses, Set roomSoltLists) {
		this.agentList = agentList;
		this.subNetNum = subNetNum;
		this.slotStatuses = slotStatuses;
		this.roomSoltLists = roomSoltLists;
	}

	// Property accessors

	public Integer getIdsoltList() {
		return this.idsoltList;
	}

	public void setIdsoltList(Integer idsoltList) {
		this.idsoltList = idsoltList;
	}

	public AgentList getAgentList() {
		return this.agentList;
	}

	public void setAgentList(AgentList agentList) {
		this.agentList = agentList;
	}

	public Integer getSubNetNum() {
		return this.subNetNum;
	}

	public void setSubNetNum(Integer subNetNum) {
		this.subNetNum = subNetNum;
	}

	public Set getSlotStatuses() {
		return this.slotStatuses;
	}

	public void setSlotStatuses(Set slotStatuses) {
		this.slotStatuses = slotStatuses;
	}

	public Set getRoomSoltLists() {
		return this.roomSoltLists;
	}

	public void setRoomSoltLists(Set roomSoltLists) {
		this.roomSoltLists = roomSoltLists;
	}

}