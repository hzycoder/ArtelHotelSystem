package com.login.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.common.pojo.SysUser;

public class UserDto {

	private Integer id;
	private SysUser creator;		//creator
	private String userCode;
	private String userAccount;
	private String userPassword;
	private String passwordSalt;
	private Short userSex;
	private String username;
	private String userPhone;
	private String userPosition;
	private Short userPermission;
	private Date createTime;
	private Date lastTime;		
	private Short retryCount;	

	public UserDto() {
		super();
	}



	public UserDto(Integer id, SysUser creator, String userCode, String userAccount, String userPassword,
			String passwordSalt, Short userSex, String username, String userPhone, String userPosition,
			Short userPermission, Date createTime, Date lastTime, Short retryCount) {
		super();
		this.id = id;
		this.creator = creator;
		this.userCode = userCode;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.passwordSalt = passwordSalt;
		this.userSex = userSex;
		this.username = username;
		this.userPhone = userPhone;
		this.userPosition = userPosition;
		this.userPermission = userPermission;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.retryCount = retryCount;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getCreator() {
		return creator;
	}

	public void setCreator(SysUser creator) {
		this.creator = creator;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Short getUserSex() {
		return userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public Short getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Short userPermission) {
		this.userPermission = userPermission;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Short getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Short retryCount) {
		this.retryCount = retryCount;
	}



	@Override
	public String toString() {
		return "UserDto [id=" + id + ", creator=" + creator + ", userCode=" + userCode + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", passwordSalt=" + passwordSalt + ", userSex=" + userSex
				+ ", username=" + username + ", userPhone=" + userPhone + ", userPosition=" + userPosition
				+ ", userPermission=" + userPermission + ", createTime=" + createTime + ", lastTime=" + lastTime
				+ ", retryCount=" + retryCount + "]";
	}

	

}
