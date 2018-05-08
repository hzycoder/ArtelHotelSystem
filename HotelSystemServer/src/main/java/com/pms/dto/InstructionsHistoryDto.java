package com.pms.dto;

import java.sql.Timestamp;

public class InstructionsHistoryDto {
	private String instructionsContent;
	private Timestamp sendTime;

	public InstructionsHistoryDto() {
	}

	public InstructionsHistoryDto(String instructionsContent, Timestamp sendTime) {
		super();
		this.instructionsContent = instructionsContent;
		this.sendTime = sendTime;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public String getInstructionsContent() {
		return instructionsContent;
	}

	public void setInstructionsContent(String instructionsContent) {
		this.instructionsContent = instructionsContent;
	}

}
