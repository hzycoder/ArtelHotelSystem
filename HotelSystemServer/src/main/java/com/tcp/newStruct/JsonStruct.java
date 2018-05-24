package com.tcp.newStruct;

import com.alibaba.fastjson.JSONObject;

public class JsonStruct {
	private int headLength;
	private JSONObject content;

	public JsonStruct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonStruct(int headLength, JSONObject content) {
		super();
		this.headLength = headLength;
		this.content = content;
	}

	public JsonStruct(JSONObject content) {
		super();
		this.headLength = content.toJSONString().getBytes().length;
		this.content = content;
	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}

	public int getHeadLength() {
		return headLength;
	}

	public void setHeadLength(int headLength) {
		this.headLength = headLength;
	}

	@Override
	public String toString() {
		return "JsonMessage [headLength=" + headLength + ", content=" + content + "]";
	}

}