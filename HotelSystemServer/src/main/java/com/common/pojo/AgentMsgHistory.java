package com.common.pojo;

import java.sql.Timestamp;

/**
 * AgentMsgHistory entity. @author MyEclipse Persistence Tools
 */

public class AgentMsgHistory implements java.io.Serializable {

	// Fields

	private Integer idAgentMsgHistory;
	private String cmdParam;
	private Timestamp cmdTime;
	private Integer agentId;

	// Constructors

	/** default constructor */
	public AgentMsgHistory() {
	}

	/** full constructor */
	public AgentMsgHistory(String cmdParam, Timestamp cmdTime, Integer agentId) {
		this.cmdParam = cmdParam;
		this.cmdTime = cmdTime;
		this.agentId = agentId;
	}

	// Property accessors

	public Integer getIdAgentMsgHistory() {
		return this.idAgentMsgHistory;
	}

	public void setIdAgentMsgHistory(Integer idAgentMsgHistory) {
		this.idAgentMsgHistory = idAgentMsgHistory;
	}

	public String getCmdParam() {
		return this.cmdParam;
	}

	public void setCmdParam(String cmdParam) {
		this.cmdParam = cmdParam;
	}

	public Timestamp getCmdTime() {
		return this.cmdTime;
	}

	public void setCmdTime(Timestamp cmdTime) {
		this.cmdTime = cmdTime;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

}