package com.tcp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.HotelList;
import com.tcp.dao.TcpDao;
import com.tcp.dto.UpgradeDto;

@Repository
public class TcpDaoImpl implements TcpDao{
	@Autowired
	SessionFactory sessionfactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<UpgradeDto> getUpgrade(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT H.hotelName AS hotelName,A.macAddress AS macAddress "
				+ "FROM HotelList AS H,HotelAgentList AS HA,AgentList AS A "
				+ "WHERE H.idHotelList = HA.HotelList_idHotelList "
				+ "AND A.idAgentList = HA.AgentList_idAgentList "
				+ "AND H.idHotelList = '"+hotelId+"' ");
		return sessionfactory.getCurrentSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(UpgradeDto.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMacAddress(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.macAddress AS macAddress "
				+ "FROM AgentList AS A,HotelAgentList AS HA "
				+ "WHERE A.idAgentList = HA.AgentList_idAgentList AND HA.HotelList_idHotelList = '"+Integer.valueOf(hotelId)+"'");
		return sessionfactory.getCurrentSession().createSQLQuery(sql.toString()).list();
	}

	@Override
	public HotelList getHotel(String hotelId) {
		return (HotelList) sessionfactory.getCurrentSession().createQuery("FROM HotelList WHERE idHotelList = :idHotelList").setInteger("idHotelList", Integer.valueOf(hotelId)).uniqueResult();
	}
	
	
}
