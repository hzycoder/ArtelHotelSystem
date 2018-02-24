package com.common.pojo;

/**
 * SoltConfigure entity. @author MyEclipse Persistence Tools
 */

public class SoltConfigure implements java.io.Serializable {

	// Fields

	private Integer idsoltConfigure;
	private AgentList agentList;
	private String fristUrl;
	private String secondUrl;
	private Integer devOlineTime;

	// Constructors

	/** default constructor */
	public SoltConfigure() {
	}

	/** full constructor */
	public SoltConfigure(AgentList agentList, String fristUrl, String secondUrl, Integer devOlineTime) {
		this.agentList = agentList;
		this.fristUrl = fristUrl;
		this.secondUrl = secondUrl;
		this.devOlineTime = devOlineTime;
	}

	// Property accessors

	public Integer getIdsoltConfigure() {
		return this.idsoltConfigure;
	}

	public void setIdsoltConfigure(Integer idsoltConfigure) {
		this.idsoltConfigure = idsoltConfigure;
	}

	public AgentList getAgentList() {
		return this.agentList;
	}

	public void setAgentList(AgentList agentList) {
		this.agentList = agentList;
	}

	public String getFristUrl() {
		return this.fristUrl;
	}

	public void setFristUrl(String fristUrl) {
		this.fristUrl = fristUrl;
	}

	public String getSecondUrl() {
		return this.secondUrl;
	}

	public void setSecondUrl(String secondUrl) {
		this.secondUrl = secondUrl;
	}

	public Integer getDevOlineTime() {
		return this.devOlineTime;
	}

	public void setDevOlineTime(Integer devOlineTime) {
		this.devOlineTime = devOlineTime;
	}

}