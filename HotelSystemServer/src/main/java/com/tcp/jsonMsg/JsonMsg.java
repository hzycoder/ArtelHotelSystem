package com.tcp.jsonMsg;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;


public class JsonMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject jsonContent;

	public JsonMsg(JSONObject jsonContent) {
		this.jsonContent = jsonContent;
	}

	public String getJsonContent() {
		return jsonContent.toJSONString();
	}

	public void setJsonContent(JSONObject jsonContent) {
		this.jsonContent = jsonContent;
	}
	
	public void print(){
		System.out.println(jsonContent.toString());
	}

}
