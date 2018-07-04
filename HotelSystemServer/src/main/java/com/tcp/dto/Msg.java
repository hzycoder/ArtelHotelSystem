package com.tcp.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class Msg {
	private String METHOD;
	private String PARM;

	public Msg() {
	}

	public Msg(String METHOD, String PARM) {
		this.METHOD = METHOD;
		this.PARM = PARM;
	}

	public Msg(String PARM) {
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
	public String getPARM() {
		return PARM;
	}

	public void setPARM(String pARM) {
		PARM = pARM;
	}

}
