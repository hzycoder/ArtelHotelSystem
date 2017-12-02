package com.common.pojo;

import java.util.Date;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

@Component("user")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class SysUser implements java.io.Serializable {

	// Fields

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
//	private Set sysHotelUsers = new HashSet(0);	//�Ƶ����������˼���
//	private Set sysHotels = new HashSet(0);	//�Ƶ����ܼ���
//	private Set sysUsers = new HashSet(0);	//�����˼���

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(Integer id) {
		this.id = id;
	}

	/** full constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public SysUser(Integer id, SysUser creator, String userCode, String userAccount, String userPassword,
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
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Short getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPosition() {
		return this.userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public Short getUserPermission() {
		return this.userPermission;
	}

	public void setUserPermission(Short userPermission) {
		this.userPermission = userPermission;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Short getRetryCount() {
		return this.retryCount;
	}

	public void setRetryCount(Short retryCount) {
		this.retryCount = retryCount;
	}

//	public Set getSysHotelUsers() {
//		return this.sysHotelUsers;
//	}
//
//	public void setSysHotelUsers(Set sysHotelUsers) {
//		this.sysHotelUsers = sysHotelUsers;
//	}
//
//	public Set getSysHotels() {
//		return this.sysHotels;
//	}
//
//	public void setSysHotels(Set sysHotels) {
//		this.sysHotels = sysHotels;
//	}
//
//	public Set getSysUsers() {
//		return this.sysUsers;
//	}
//
//	public void setSysUsers(Set sysUsers) {
//		this.sysUsers = sysUsers;
//	}

}