package com.hotel.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.hotel.dao.HotelDao;
import com.login.dto.UserDto;
@Repository
public class HotelDaoImpl implements HotelDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	LoginUserList user;

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotels(UserDto user) {
		return sessionFactory.getCurrentSession().createQuery("FROM SysHotel where hotelManager = ?")
				.setParameter(0, user.getId()).list();
//		return SessionFactory.getcurr
	}

	@Override
	public Integer getHotelCounts(UserDto user) {
		long temp = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM SysHotel WHERE hotelManager = ?").setParameter(0, user.getId()).uniqueResult();
		return (int) temp;
	}

}
