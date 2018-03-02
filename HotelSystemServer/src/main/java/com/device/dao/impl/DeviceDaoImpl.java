package com.device.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.device.dao.DeviceDao;

@Repository
public class DeviceDaoImpl implements DeviceDao {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelAgentList> getHotelAgentListByHotelId(String hotelId) {
		List<HotelAgentList> hotelAgentList =  sessionFactory.getCurrentSession().createQuery("FROM HotelAgentList WHERE idHotelList = ?").setParameter(0, Integer.parseInt(hotelId)).list();
		return hotelAgentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AgentList getAgentListByAgentId(int agentId) {
		return (AgentList) sessionFactory.getCurrentSession().createQuery("FROM AgentList WHERE idAgentList = ?").setParameter(0, agentId).uniqueResult();
	}
	

}
