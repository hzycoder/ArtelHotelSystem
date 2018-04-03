package com.tcp;

import java.util.Arrays;

public class FrameStruct {

	private int headData = ConstantValue.HEAD_DATA;
	private int contentLength;
	private byte[] content;

	public FrameStruct(int datalength, byte[] data) {
		this.contentLength = datalength;
		this.content = new byte[datalength];
		System.arraycopy(data, 0, content, 0, datalength);
	}

	public FrameStruct(int headdata, int contentlength, byte[] data) {
		this(contentlength, data);
		this.headData = headdata;
	}

	public int getHeadData() {
		return headData;
	}

	public void setHeadData(int headData) {
		this.headData = headData;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FrameStruct [headData=" + headData + ", contentLength=" + contentLength + ", content="
				+ Arrays.toString(content) + "]";
	}

}
