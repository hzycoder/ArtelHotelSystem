package com.common.pojo;

import java.sql.Timestamp;

/**
 * LoginUserInfo entity. @author MyEclipse Persistence Tools
 */

public class LoginUserInfo implements java.io.Serializable {

	// Fields

	private Integer idLoginUserInfo;
	private LoginUserList loginUserList;
	private String operation;
	private Timestamp time;
	private String comment;

	// Constructors

	/** default constructor */
	public LoginUserInfo() {
	}

	/** minimal constructor */
	public LoginUserInfo(LoginUserList loginUserList, String operation, Timestamp time) {
		this.loginUserList = loginUserList;
		this.operation = operation;
		this.time = time;
	}

	/** full constructor */
	public LoginUserInfo(LoginUserList loginUserList, String operation, Timestamp time, String comment) {
		this.loginUserList = loginUserList;
		this.operation = operation;
		this.time = time;
		this.comment = comment;
	}

	// Property accessors

	public Integer getIdLoginUserInfo() {
		return this.idLoginUserInfo;
	}

	public void setIdLoginUserInfo(Integer idLoginUserInfo) {
		this.idLoginUserInfo = idLoginUserInfo;
	}

	public LoginUserList getLoginUserList() {
		return this.loginUserList;
	}

	public void setLoginUserList(LoginUserList loginUserList) {
		this.loginUserList = loginUserList;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}