package com.hotel.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<SysHotel> gethotels(SysUser user) {
		return sessionFactory.getCurrentSession().createQuery("FROM SysHotel where hotelManager = ?")
				.setParameter(0, user.getId()).list();
//		return SessionFactory.getcurr
	}

}
