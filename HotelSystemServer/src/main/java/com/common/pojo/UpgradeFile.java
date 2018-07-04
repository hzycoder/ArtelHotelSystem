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
	private String fileName;

	// Constructors

	/** default constructor */
	public UpgradeFile() {
	}

	/** full constructor */

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public UpgradeFile(Integer fileId, byte[] fileContent, Timestamp uploadTime, String fileName) {
		super();
		this.fileId = fileId;
		this.fileContent = fileContent;
		this.uploadTime = uploadTime;
		this.fileName = fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UpgradeFile [fileId=" + fileId + ", fileContent=" + Arrays.toString(fileContent) + ", uploadTime="
				+ uploadTime + ", fileName=" + fileName + "]";
	}

}