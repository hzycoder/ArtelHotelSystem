package com.common.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SoltList entity. @author MyEclipse Persistence Tools
 */

public class SoltList implements java.io.Serializable {

	// Fields

	private Integer idsoltList;
	private Integer agentId;
	private Integer subNetNum;

	// Constructors

	/** default constructor */
	public SoltList() {
	}


	// Property accessors

	public Integer getIdsoltList() {
		return this.idsoltList;
	}

	public void setIdsoltList(Integer idsoltList) {
		this.idsoltList = idsoltList;
	}
	

	public Integer getAgentId() {
		return agentId;
	}


	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}


	public Integer getSubNetNum() {
		return this.subNetNum;
	}

	public void setSubNetNum(Integer subNetNum) {
		this.subNetNum = subNetNum;
	}

}