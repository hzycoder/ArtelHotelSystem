package com.hotel.dao;

import java.util.List;
import com.common.pojo.HotelList;
import com.login.dto.UserDto;

public interface HotelDao {
	public List<HotelList> gethotels(UserDto user);
	public Integer getHotelCounts(UserDto user);

}
