package com.common.services;

import java.util.Map;

public interface CommonService {
	public boolean verifyAccount(String account);
	public boolean verifyHotelId(String hotelId);
	public Map<String,Object> export();
	public Map<String,Object> exportSlotStauts(String hotelId);
}
