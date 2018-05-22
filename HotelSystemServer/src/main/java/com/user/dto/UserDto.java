package com.user.dto;

import java.sql.Timestamp;

public class UserDto {

	private Integer idUserList;
	private String account;
	// private String password;
	// private String passwordSalt;
	private String userName;
	private String userPhone;
	private String position;
	private String permission;
	private String creator;
	private Timestamp createTime;

	public UserDto() {
	}

	public Integer getIdUserList() {
		return idUserList;
	}

	public void setIdUserList(Integer idUserList) {
		this.idUserList = idUserList;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
