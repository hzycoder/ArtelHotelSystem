package com.device.dto;

public class BindingHotelDto {
	private Integer hotelId;
	private String hotelCode;
	private String hotelName;
	private String hotelAddress;

	public BindingHotelDto() {
		super();
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	@Override
	public String toString() {
		return "BindingHotelDto [hotelId=" + hotelId + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName
				+ ", hotelAddress=" + hotelAddress + "]";
	}

}
