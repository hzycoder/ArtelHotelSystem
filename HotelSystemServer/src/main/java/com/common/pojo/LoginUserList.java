package com.common.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * LoginUserList entity. @author MyEclipse Persistence Tools
 */
@Component("user")
public class LoginUserList implements java.io.Serializable {

	// Fields

	private Integer idUserList;
	private String account;
	private String password;
	private String passwordSalt;
	private String userName;
	private String userId;
	private String userPhone;
	private String position;
	private Integer permission;
	private String creator;
	private Timestamp createTime;
//	private Set userHotelLists = new HashSet(0);
//	private Set loginUserInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public LoginUserList() {
	}

	/** minimal constructor */
	public LoginUserList(String account, String password, String passwordSalt, String userName, String userId,
			Integer permission, Timestamp createTime) {
		this.account = account;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.userName = userName;
		this.userId = userId;
		this.permission = permission;
		this.createTime = createTime;
	}

	/** full constructor */
	public LoginUserList(String account, String password, String passwordSalt, String userName, String userId,
			String userPhone, String position, Integer permission, String creator, Timestamp createTime,
			Set userHotelLists, Set loginUserInfos) {
		this.account = account;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.userName = userName;
		this.userId = userId;
		this.userPhone = userPhone;
		this.position = position;
		this.permission = permission;
		this.creator = creator;
		this.createTime = createTime;
//		this.userHotelLists = userHotelLists;
//		this.loginUserInfos = loginUserInfos;
	}

	// Property accessors

	public Integer getIdUserList() {
		return this.idUserList;
	}

	public void setIdUserList(Integer idUserList) {
		this.idUserList = idUserList;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

//	public Set getUserHotelLists() {
//		return this.userHotelLists;
//	}
//
//	public void setUserHotelLists(Set userHotelLists) {
//		this.userHotelLists = userHotelLists;
//	}
//
//	public Set getLoginUserInfos() {
//		return this.loginUserInfos;
//	}
//
//	public void setLoginUserInfos(Set loginUserInfos) {
//		this.loginUserInfos = loginUserInfos;
//	}

}