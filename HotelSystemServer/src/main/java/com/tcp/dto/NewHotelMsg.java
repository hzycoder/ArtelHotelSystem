package com.tcp.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class NewHotelMsg {
	private String METHOD = "HOTEL_LIST";
	private String HOTEL_LIST;

	public NewHotelMsg() {
		super();
	}

	public NewHotelMsg(String hOTEL_LIST) {
		super();
		this.METHOD = "HOTEL_LIST";
		this.HOTEL_LIST = hOTEL_LIST;
	}

	@JSONField(name = "METHOD")
	public String getMETHOD() {
		return METHOD;
	}

	public void setMETHOD(String METHOD) {
		this.METHOD = METHOD;
	}

	@JSONField(name = "HOTEL_LIST")
	public String getHOTEL_LIST() {
		return HOTEL_LIST;
	}

	public void setHOTEL_LIST(String HOTEL_LIST) {
		this.HOTEL_LIST = HOTEL_LIST;
	}

}
