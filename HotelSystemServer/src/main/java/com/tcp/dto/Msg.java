package com.tcp.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class Msg {
	private String METHOD;
	private Object PARM;

	public Msg() {
	}

	public Msg(String METHOD, Object PARM) {
		this.METHOD = METHOD;
		this.PARM = PARM;
	}

	public Msg(Object PARM) {
		this.METHOD = "TRANSPOND";
		this.PARM = PARM;
	}

	@JSONField(name = "METHOD")
	public String getMETHOD() {
		return METHOD;
	}

	public void setMETHOD(String METHOD) {
		this.METHOD = METHOD;
	}

	@JSONField(name = "PARM")
	public Object getPARM() {
		return PARM;
	}

	public void setPARM(Object PARM) {
		this.PARM = PARM;
	}

}
