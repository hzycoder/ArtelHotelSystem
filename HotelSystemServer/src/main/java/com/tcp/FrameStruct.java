package com.tcp;

import java.util.Arrays;

public class FrameStruct {
	private int syncData;// 同步字节 4字节
	private byte type; // 数据类型 1字节
	private int bodyLength;// 包长度 5字节 最大表示512K
	private byte[] body; // 数据内容

	public FrameStruct(int syncData, byte type, int bodyLength, byte[] body) {
		super();
		this.syncData = syncData;
		this.type = type;
		this.bodyLength = bodyLength;
		this.body = body;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getHeadData() {
		return syncData;
	}

	public void setHeadData(int headData) {
		this.syncData = headData;
	}

	public int getContentLength() {
		return bodyLength;
	}

	public void setContentLength(int contentLength) {
		this.bodyLength = contentLength;
	}

	public byte[] getContent() {
		return body;
	}

	public void setContent(byte[] content) {
		this.body = content;
	}

	@Override
	public String toString() {
		return "FrameStruct [syncData=" + syncData + ", type=" + type + ", bodyLength=" + bodyLength + ", body="
				+ Arrays.toString(body) + "]";
	}
	
	

}
