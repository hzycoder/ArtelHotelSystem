package com.tcp.jsonMsg;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class JsonMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSONObject jsonObject;

	public JsonMsg(String type) {
		if (type.equals("a")) {
			String jsonString = "{\"卡号\":\"10010\",\"卡槽号\":\"1111120\",\"发送时间\":\""+new Date().getTime()+"\"}";
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			this.jsonObject = jsonObject;
		} else if (type.equals("b")) {
			String jsonString = "{\"卡号\":\"10011\",\"卡槽号\":\"1111121\",\"发送时间\":\""+new Date().getTime()+"\"}";
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			this.jsonObject = jsonObject;
		} else if (type.equals("c")) {
			String jsonString = "{\"卡号\":\"10012\",\"卡槽号\":\"1111122\",\"发送时间\":\""+new Date().getTime()+"\"}";
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			this.jsonObject = jsonObject;
		} else if (type.equals("d")) {
			String jsonString = "{\"卡号\":\"10013\",\"卡槽号\":\"1111123\",\"发送时间\":\""+new Date().getTime()+"\"}";
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			this.jsonObject = jsonObject;
		}

	}

	public JsonMsg(JSONObject jsonContent) {
		this.jsonObject = jsonContent;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public void print() {
		System.out.println(jsonObject.toString());
	}

}
