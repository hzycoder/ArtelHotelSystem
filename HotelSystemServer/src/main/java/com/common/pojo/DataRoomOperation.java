package com.common.pojo;

import java.util.Date;

/**
 * DataRoomOperation entity. @author MyEclipse Persistence Tools
 */

public class DataRoomOperation implements java.io.Serializable {

	// Fields

	private Long id;
	private SysRoom sysRoom;
	private SysHotel sysHotel;
	private Short operationType;
	private Date operationTime;
	private String operationComment;
	private Short operationReview;

	// Constructors

	/** default constructor */
	public DataRoomOperation() {
	}

	/** full constructor */
	public DataRoomOperation(SysRoom sysRoom, SysHotel sysHotel, Short operationType, Date operationTime,
			String operationComment, Short operationReview) {
		this.sysRoom = sysRoom;
		this.sysHotel = sysHotel;
		this.operationType = operationType;
		this.operationTime = operationTime;
		this.operationComment = operationComment;
		this.operationReview = operationReview;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysRoom getSysRoom() {
		return this.sysRoom;
	}

	public void setSysRoom(SysRoom sysRoom) {
		this.sysRoom = sysRoom;
	}

	public SysHotel getSysHotel() {
		return this.sysHotel;
	}

	public void setSysHotel(SysHotel sysHotel) {
		this.sysHotel = sysHotel;
	}

	public Short getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Short operationType) {
		this.operationType = operationType;
	}

	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationComment() {
		return this.operationComment;
	}

	public void setOperationComment(String operationComment) {
		this.operationComment = operationComment;
	}

	public Short getOperationReview() {
		return this.operationReview;
	}

	public void setOperationReview(Short operationReview) {
		this.operationReview = operationReview;
	}

}