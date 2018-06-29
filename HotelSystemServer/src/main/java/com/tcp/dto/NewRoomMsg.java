package com.tcp.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class NewRoomMsg {
	private String METHOD = "ROOM_LIST";
	private String HOTEL_ID;
	private String ROOM_LIST;

	public NewRoomMsg() {
		super();
	}

	public NewRoomMsg(String hOTEL_ID, String rOOM_LIST) {
		this.METHOD = "ROOM_LIST";
		this.HOTEL_ID = hOTEL_ID;
		this.ROOM_LIST = rOOM_LIST;
	}

	@JSONField(name = "METHOD")
	public String getMETHOD() {
		return METHOD;
	}

	public void setMETHOD(String mETHOD) {
		METHOD = mETHOD;
	}

	@JSONField(name = "HOTEL_ID")
	public String getHOTEL_ID() {
		return HOTEL_ID;
	}

	public void setHOTEL_ID(String hOTEL_ID) {
		HOTEL_ID = hOTEL_ID;
	}

	@JSONField(name = "ROOM_LIST")
	public String getROOM_LIST() {
		return ROOM_LIST;
	}

	public void setROOM_LIST(String rOOM_LIST) {
		ROOM_LIST = rOOM_LIST;
	}

}
