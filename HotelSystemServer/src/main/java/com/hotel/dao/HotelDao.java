package com.hotel.dao;

import java.util.List;

import com.common.pojo.SysHotel;
import com.common.pojo.SysUser;

public interface HotelDao {
	public List<SysHotel> gethotels(SysUser user);

}
