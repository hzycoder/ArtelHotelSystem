package com.pms.dto;

public class RequestDto {
	private String sign;
	private String timestamp;
	private Object data;
	private String requestType;

	public RequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "RequestDto [sign=" + sign + ", timestamp=" + timestamp + ", data=" + data + ", requestType="
				+ requestType + "]";
	}

}
