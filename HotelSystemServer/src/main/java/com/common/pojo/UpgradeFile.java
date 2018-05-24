package com.common.pojo;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * File entity. @author MyEclipse Persistence Tools
 */

public class UpgradeFile implements java.io.Serializable {

	// Fields

	private Integer fileId;
	private byte[] fileContent;
	private Timestamp uploadTime;

	// Constructors

	/** default constructor */
	public UpgradeFile() {
	}

	/** full constructor */
	public UpgradeFile(byte[] fileContent, Timestamp uploadTime) {
		this.fileContent = fileContent;
		this.uploadTime = uploadTime;
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "UpgradeFile [fileId=" + fileId + ", fileContent=" + Arrays.toString(fileContent) + ", uploadTime="
				+ uploadTime + "]";
	}
	
	

}