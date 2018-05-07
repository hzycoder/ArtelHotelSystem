package com.tcp.jsonMsg;

import java.io.Serializable;
import java.util.Date;


public class JsonMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonContent;

	public JsonMsg(String type) {
		if (type.equals("a")) {
			String jsonString = "{\"卡号\":\"10010\",\"卡槽号\":\"1111120\",\"发送时间\":\"" + new Date().getTime() + "\"}";
			this.jsonContent = jsonString;
		} else if (type.equals("b")) {
			String jsonString = "{\"卡号\":\"10011\",\"卡槽号\":\"1111121\",\"发送时间\":\"" + new Date().getTime() + "\"}";
			this.jsonContent = jsonString;
		} else if (type.equals("c")) {
			String jsonString = "{\"卡号\":\"10012\",\"卡槽号\":\"1111122\",\"发送时间\":\"" + new Date().getTime() + "\"}";
			this.jsonContent = jsonString;
		} else if (type.equals("d")) {
			String jsonString = "{\"卡号\":\"10013\",\"卡槽号\":\"1111123\",\"发送时间\":\"" + new Date().getTime() + "\"}";
			this.jsonContent = jsonString;
		}

	}

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void print() {
		System.out.println(jsonContent.toString());
	}

}