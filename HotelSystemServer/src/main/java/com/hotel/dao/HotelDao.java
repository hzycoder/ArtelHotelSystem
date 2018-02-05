package com.hotel.dao;

import java.util.List;

import com.common.pojo.SysHotel;
import com.common.pojo.SysUser;
import com.login.dto.UserDto;

public interface HotelDao {
	public List<SysHotel> gethotels(UserDto user);
	public Integer getHotelCounts(UserDto user);

}
