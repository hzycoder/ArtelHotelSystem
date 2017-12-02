package com.hotel.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.pojo.SysHotel;
import com.common.pojo.SysUser;
import com.hotel.dao.HotelDao;

public class HotelDaoImpl implements HotelDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	SysUser user;

	@Override
	public SysHotel gethotels(SysUser user) {
//		return SessionFactory.getcurr
		return null;
	}

}
