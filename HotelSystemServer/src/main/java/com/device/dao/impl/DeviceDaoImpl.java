package com.device.dao.impl;

import java.util.List;

import javax.management.Query;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.AgentList;
import com.common.pojo.HotelAgentList;
import com.common.pojo.RoomList;
import com.common.pojo.RoomSoltList;
import com.common.pojo.SoltList;
import com.common.pojo.UpgradeFile;
import com.device.dao.DeviceDao;
import com.device.dto.AgentDto;
import com.device.dto.BindingAgentDto;
import com.device.dto.BindingHotelDto;
import com.device.dto.BindingRoomDto;
import com.device.dto.DeviceDto;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
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
	 * 
	 * @param agentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceDto> getSoltByAgentId(String agentId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "R.idRoomList idRoomList," + "R.RoomID roomId," + "R.roomNum roomNum," + "R.floor floor,"
				+ "R.HotelList_idHotelList hotelId," + "S.idsoltList idSoltList," + "S.subNetNum subNetNum,"
				+ "S.AgentList_idAgentList agentId " + "FROM " + "SoltList AS S," + "RoomSoltList AS RS,"
				+ "RoomList AS R " + "WHERE " + "RS.soltList_idsoltList " + "= " + "S.idsoltList " + "AND "
				+ "RS.RoomList_idRoomList " + "= " + "R.idRoomList " + "AND " + "S.AgentList_idAgentList = '" + agentId
				+ "'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).addScalar("idRoomList")
				.addScalar("roomId").addScalar("roomNum").addScalar("floor").addScalar("hotelId")
				.addScalar("idSoltList").addScalar("subNetNum").addScalar("agentId")
				.setResultTransformer(Transformers.aliasToBean(DeviceDto.class)).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgentList> getAgentListByHotelId(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * " + "FROM " + "AgentList " + "WHERE " + "idAgentList " + "IN " + "(SELECT "
				+ "AgentList_idAgentList " + "FROM " + "HotelAgentList " + "WHERE " + "HotelList_idHotelList = '"
				+ hotelId + "')");
		List<AgentList> agentList = sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addEntity(AgentList.class).list();
		return agentList;
	}

	/**
	 * 获取中继数量
	 * 
	 * @param hotelId
	 * @return
	 */
	@Override
	public Integer getAgentCount(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "COUNT(*) " + "FROM " + "HotelAgentList HA " + "WHERE " + "HA.HotelList_idHotelList = "
				+ "'" + hotelId + "'");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 获取设备数量包括中继
	 * 
	 * @param hotelId
	 * @return
	 */
	@Override
	public Integer getDeviceCount(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "COUNT(*) " + "FROM " + "SoltList S " + "WHERE " + "S.AgentList_idAgentList " + "IN "
				+ "(" + "SELECT " + "HA.AgentList_idAgentList " + "FROM " + "HotelAgentList HA " + "WHERE "
				+ "HA.HotelList_idHotelList=" + "'" + hotelId + "')");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceDto> getAgentByInn(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT R.idRoomList idRoomList," + "R.RoomID roomId," + "R.roomNum roomNum," + "R.floor floor,"
				+ "R.HotelList_idHotelList hotelId," + "S.idsoltList idSoltList," + "S.subNetNum subNetNum,"
				+ "S.AgentList_idAgentList agentId " + "FROM " + "SoltList S," + "RoomSoltList RS," + "RoomList R "
				+ "WHERE " + "RS.RoomList_idRoomList=R.idRoomList " + "AND " + "RS.soltList_idsoltList = S.idsoltList "
				+ "AND " + "S.AgentList_idAgentList " + "IN(" + "SELECT " + "HA.AgentList_idAgentList " + "FROM "
				+ "HotelAgentList HA " + "WHERE " + "HA.HotelList_idHotelList=" + "'" + Integer.valueOf(hotelId)
				+ "')");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).addScalar("idRoomList")
				.addScalar("roomId").addScalar("roomNum").addScalar("floor").addScalar("hotelId")
				.addScalar("idSoltList").addScalar("subNetNum").addScalar("agentId")
				.setResultTransformer(Transformers.aliasToBean(DeviceDto.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgentDto> getAgentAndRoomRelations(String agentId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.idAgentList idAgentList," + "A.macAddress macAddress," + "A.createTime createTime,"
				+ "A.deviceCount deviceCount," + "RS.idRoomSlotList idRoomSlotList,"
				+ "RS.soltList_idsoltList idsoltList," + "RS.RoomList_idRoomList idRoomList," + "R.RoomID roomId,"
				+ "R.roomNum roomNum," + "R.floor floor," + "R.HotelList_idHotelList hotelId " + "FROM "
				+ "RoomSoltList RS," + "AgentList A," + "RoomList R " + "WHERE " + "RS.soltList_idsoltList = ("
				+ "SELECT " + "S.idsoltList " + "FROM " + "SoltList S " + "WHERE " + "S.AgentList_idAgentList" + "="
				+ "'" + agentId + "'" + " AND " + "A.idAgentList" + "=" + "'" + agentId + "'" + " AND "
				+ "S.subNetNum='0'" + "AND " + "R.idRoomList " + "=" + " RS.RoomList_idRoomList)");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).addScalar("idAgentList")
				.addScalar("macAddress").addScalar("createTime").addScalar("deviceCount").addScalar("idRoomSlotList")
				.addScalar("idRoomList").addScalar("idsoltList").addScalar("roomId").addScalar("roomNum")
				.addScalar("floor").addScalar("hotelId").setResultTransformer(Transformers.aliasToBean(AgentDto.class))
				.list();
	}

	@Override
	public Integer getslotIdByAgentId(String agentId) {
		return (Integer) sessionFactory.getCurrentSession()
				.createQuery("SELECT idsoltList FROM SoltList WHERE agentId = ? AND subNetNum =0")
				.setParameter(0, Integer.valueOf(agentId)).uniqueResult();
	}

	@Override
	public void saveFile(UpgradeFile file) {
		sessionFactory.getCurrentSession().saveOrUpdate(file);
	}
	
	@Override
	public void updateRoomList(Integer roomID, Integer subNet) {
		StringBuffer sql = new StringBuffer();
		sql.append("update RoomList set soltNum='" + subNet + "' where idRoomList = '" + roomID + "'");
		sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
	}

//	@Override
//	public void binding(RoomSoltList rsList,String subNet) {
//		sessionFactory.getCurrentSession().save(rsList);
//		StringBuffer sql = new StringBuffer();
//		sql.append("update RoomList set soltNum='"+subNet+"' where idRoomList = '"+rsList.getIdRoomList()+"'");
//		int retsult = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
//		System.out.println(sql.toString()+"===="+retsult);
//	}

	@Override
	public BindingAgentDto getAgentBySlotId(String slotId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "
				+ "S.idsoltList AS slotId,"
				+ "S.subNetNum AS subNetNum,"
				+ "S.AgentList_idAgentList AS agentId,"
				+ "A.macAddress AS macAddress,"
				+ "A.createTime AS agentCreateTime,"
				+ "A.deviceCount AS deviceCount "
				+ "FROM "
				+ "SoltList AS S LEFT JOIN AgentList AS A "
				+ "ON "
				+ "A.idAgentList = S.AgentList_idAgentList "
				+ "WHERE S.idsoltList = "
				+ "'"+slotId+"'");
		return (BindingAgentDto) sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
				.addScalar("slotId")
				.addScalar("subNetNum")
				.addScalar("agentId")
				.addScalar("macAddress")
				.addScalar("agentCreateTime")
				.addScalar("deviceCount")
				.setResultTransformer(Transformers.aliasToBean(BindingAgentDto.class)).uniqueResult();
	}

	@Override
	public BindingHotelDto getHotelByAgentId(String agentId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "
				+ "H.idHotelList AS hotelId,"
				+ "H.hotelID AS hotelCode,"
				+ "H.hotelName,"
				+ "H.address AS hotelAddress "
				+ "FROM "
				+ "HotelAgentList AS HA LEFT JOIN HotelList AS H "
				+ "ON "
				+ "HA.HotelList_idHotelList = H.idHotelList "
				+ "WHERE HA.AgentList_idAgentList = '"+agentId+"'");
		return (BindingHotelDto) sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
				.addScalar("hotelId")
				.addScalar("hotelCode")
				.addScalar("hotelName")
				.addScalar("hotelAddress")
				.setResultTransformer(Transformers.aliasToBean(BindingHotelDto.class)).uniqueResult();
	}

	@Override
	public BindingRoomDto getRoomBySlotId(String SlotId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "
				+ "R.RoomID AS roomCode,"
				+ "R.roomNum,"
				+ "R.floor "
				+ "FROM "
				+ "RoomSoltList AS RS LEFT JOIN RoomList AS R "
				+ "ON "
				+ "RS.RoomList_idRoomList = R.idRoomList "
				+ "WHERE "
				+ "RS.soltList_idsoltList = '"+SlotId+"'");
		return (BindingRoomDto) sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
				.addScalar("roomCode")
				.addScalar("roomNum")
				.addScalar("floor")
				.setResultTransformer(Transformers.aliasToBean(BindingRoomDto.class)).uniqueResult();
	}

	@Override
	public RoomSoltList getRoomSoltListBySlotId(Integer slotId) {
		return (RoomSoltList) sessionFactory.getCurrentSession().createQuery("FROM RoomSoltList WHERE idsoltList =:slotId")
		.setInteger("slotId", slotId).uniqueResult();
	}

	@Override
	public HotelAgentList getHotelAgentListByAgentId(Integer agentId) {
		return (HotelAgentList) sessionFactory.getCurrentSession().createQuery("FROM HotelAgentList WHERE idAgentList =:agentId")
				.setInteger("agentId", agentId).uniqueResult();
	}

	@Override
	public SoltList getSlotListBySlotId(Integer slotId) {
		return (SoltList) sessionFactory.getCurrentSession().createQuery("FROM SoltList WHERE idsoltList =:slotId")
				.setInteger("slotId", slotId).uniqueResult();
	}

	@Override
	public void saveInstance(Object o) {
		sessionFactory.getCurrentSession().save(o);
	}

	@Override
	public void updateRoomSlotList(RoomSoltList roomSoltList) {
		sessionFactory.getCurrentSession().createQuery("UPDATE RoomSoltList SET idRoomList = :roomId WHERE idsoltList =:slotId")
		.setInteger("roomId", roomSoltList.getIdRoomList())
		.setInteger("slotId", roomSoltList.getIdsoltList());
	}

	@Override
	public void updateHotelAgentList(HotelAgentList hotelAgentList) {
		sessionFactory.getCurrentSession().createQuery("UPDATE HotelAgentList SET idHotelList = :hotelId WHERE idAgentList =:agentId")
		.setInteger("hotelId", hotelAgentList.getIdHotelList())
		.setInteger("agentId", hotelAgentList.getIdAgentList());
	}
	
}
