package com.common.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.dao.CommonDao;
import com.common.dto.AllDataDto;
import com.common.dto.SlotStatusDto;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Integer getCount(String sql) {
		long temp = (Long) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
		return (int) temp;
	}
	@Override
	public List<?> getOneLine(String sql) {
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AllDataDto> export() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT H.hotelName AS hotelName,"
				+ "H.address AS hotelAddress,"
				+ "H.hotelID AS hotelCode,"
				+ "H.hotelPhone AS hotelPhone,"
				+ "H.createTime AS hotelCreateTime,"
				+ "H.STATUS AS hotelStatus,"
				+ "L.userName AS userName,"
				+ "L.account AS userAccount,"
				+ "L.userPhone AS userPhone,"
				+ "P.pmsName AS pmsName,"
				+ "(SELECT COUNT(*) FROM RoomList AS R WHERE R.HotelList_idHotelList = H.idHotelList) AS roomCount,"
				+ "(SELECT COUNT(*) FROM AgentList AS A,HotelAgentList AS HA WHERE A.idAgentList = HA.HotelList_idHotelList AND HA.HotelList_idHotelList = H.idHotelList) AS deviceCount "
				+ "FROM HotelList AS H,"
				+ "LoginUserList AS L,"
				+ "PMSList AS P "
				+ "WHERE H.hotelManager = L.idUserList "
				+ "AND "
				+ "P.pmsId = H.pmsId");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("hotelName")
				.addScalar("hotelAddress")
				.addScalar("hotelCode")
				.addScalar("hotelPhone")
				.addScalar("hotelCreateTime")
				.addScalar("hotelStatus")
				.addScalar("userName")
				.addScalar("userAccount")
				.addScalar("userPhone")
				.addScalar("pmsName")
				.addScalar("roomCount")
				.addScalar("deviceCount").setResultTransformer(Transformers.aliasToBean(AllDataDto.class)).list();
	}
	@Override
	public List<SlotStatusDto> exportSlotStatus(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT R.roomNum AS roomNum,"
				+ "CASE WHEN SS.SlotStatus = 0 THEN '插卡' ELSE '供电' END AS slotStatus,"
				+ "CASE WHEN SS.is_slot_illegal = 0 THEN '非法用电' ELSE '正常供电' END AS isSlotIllegal,"
				+ "CASE WHEN SS.lockStatus = '0' THEN '开门' ELSE '关门' END AS lockStatus,"
				+ "CASE WHEN SS.is_roomLight_on = 0 THEN '关灯' ELSE '开灯' END AS isRoomLightOn,"
				+ "CASE WHEN SS.is_device_online = 0 THEN '离线' ELSE '正常' END AS isDeviceOnline,"
				+ "SS.time "
				+ "FROM RoomList AS R,"
				+ "SoltList AS S,"
				+ "RoomSoltList AS RS,"
				+ "slotStatus AS SS "
				+ "WHERE S.idsoltList = RS.soltList_idsoltList "
				+ "AND R.idRoomList = RS.RoomList_idRoomList "
				+ "AND SS.idslotList = S.idsoltList "
				+ "AND R.HotelList_idHotelList = '"+hotelId+"'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(SlotStatusDto.class)).list();
	}
	
	
}