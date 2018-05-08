package com.common.pojo;

import java.sql.Timestamp;

/**
 * Pmsinstru entity. @author MyEclipse Persistence Tools
 */

public class Pmsinstru implements java.io.Serializable {

	// Fields

	private Integer id;
	private String instructionsContent;
	private Timestamp sendTime;
	private Integer pmsId;

	// Constructors

	/** default constructor */
	public Pmsinstru() {
	}

	/** full constructor */
	public Pmsinstru(String instructionsContent, Timestamp sendTime, Integer pmsId) {
		this.instructionsContent = instructionsContent;
		this.sendTime = sendTime;
		this.pmsId = pmsId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstructionsContent() {
		return this.instructionsContent;
	}

	public void setInstructionsContent(String instructionsContent) {
		this.instructionsContent = instructionsContent;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getPmsId() {
		return this.pmsId;
	}

	public void setPmsId(Integer pmsId) {
		this.pmsId = pmsId;
	}

}