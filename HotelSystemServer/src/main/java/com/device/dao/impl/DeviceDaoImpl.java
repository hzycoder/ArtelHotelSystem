package com.device.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.common.pojo.RoomList;
import com.common.pojo.SoltList;
import com.device.dao.DeviceDao;
import com.device.dto.DeviceDto;

import javassist.convert.Transformer;

/**
 * @author huangzhenyang
 *
 */
@Repository
public class DeviceDaoImpl implements DeviceDao {
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * 根据agentId找出对应卡槽的对应房间
	 * @param agentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceDto> getSoltByAgentId(String agentId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "R.idRoomList idRoomList,"
				+ "R.RoomID roomId,"
				+ "R.roomNum roomNum,"
				+ "R.soltNum soltNum,"
				+ "R.floor floor,"
				+ "R.HotelList_idHotelList hotelId,"
				+ "S.idsoltList idSoltList,"
				+ "S.subNetNum subNetNum,"
				+ "S.AgentList_idAgentList agentId "
				+ "FROM "
				+ "SoltList AS S,"
				+ "RoomSoltList AS RS,"
				+ "RoomList AS R "
				+ "WHERE "
				+ "RS.soltList_idsoltList "
				+ "= "
				+ "S.idsoltList "
				+ "AND "
				+ "RS.RoomList_idRoomList "
				+ "= "
				+ "R.idRoomList "
				+ "AND "
				+ "S.AgentList_idAgentList = '"+agentId+"'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("idRoomList")
				.addScalar("roomId")
				.addScalar("roomNum")
				.addScalar("soltNum")
				.addScalar("floor")
				.addScalar("hotelId")
				.addScalar("idSoltList")
				.addScalar("subNetNum")
				.addScalar("agentId").setResultTransformer(Transformers.aliasToBean(DeviceDto.class)).list();
				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgentList> getAgentListByHotelId(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * "
				+ "FROM "
				+ "AgentList "
				+ "WHERE "
				+ "idAgentList "
				+ "IN "
				+ "(SELECT "
				+ "AgentList_idAgentList "
				+ "FROM "
				+ "HotelAgentList "
				+ "WHERE "
				+ "HotelList_idHotelList = '"+hotelId+"')");
		List<AgentList> agentList =  sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).addEntity(AgentList.class).list();
		return agentList;
	}

	/**
	 * 获取中继数量
	 * @param hotelId
	 * @return
	 */
	@Override
	public Integer getAgentCount(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "COUNT(*) "
				+ "FROM "
				+ "HotelAgentList HA "
				+ "WHERE "
				+ "HA.HotelList_idHotelList = "
				+ "'"
				+ hotelId
				+ "'");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 获取设备数量包括中继
	 * @param hotelId
	 * @return
	 */
	@Override
	public Integer getDeviceCount(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "COUNT(*) "
				+ "FROM "
				+ "SoltList S "
				+ "WHERE "
				+ "S.AgentList_idAgentList "
				+ "IN "
				+ "("
				+ "SELECT "
				+ "HA.AgentList_idAgentList "
				+ "FROM "
				+ "HotelAgentList HA "
				+ "WHERE "
				+ "HA.HotelList_idHotelList="
				+ "'"
				+ hotelId
				+ "')");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}
	
	

}
