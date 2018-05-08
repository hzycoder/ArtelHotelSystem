package com.pms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pms.dao.PMSDao;
import com.pms.dto.HotelDto;
import com.pms.dto.InstructionsHistoryDto;
import com.pms.dto.RoomStatusDto;

@Repository
public class PMSDaoImpl implements PMSDao {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDto> getHotelList(Integer pmsId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "h.hotelID as hotelCode,"
				+ "h.hotelName as hotelName,"
				+ "h.address as hotelAddress,"
				+ "h.hotelPhone as hotelPhone"
				+ " FROM HotelList AS H"
				+ " WHERE H.pmsId = '"+pmsId+"'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("hotelCode")
				.addScalar("hotelName")
				.addScalar("hotelAddress")
				.addScalar("hotelPhone").setResultTransformer(Transformers.aliasToBean(HotelDto.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomStatusDto> getRommStatusListByHotelCode(String hotelCode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT '"
				+ hotelCode 
				+ "' AS hotelCode,"
				+ "R.RoomID AS roomCode,"
				+ "R.roomNum AS roomNum,"
				+ "R.floor AS roomFloor,"
				+ "SS.slotStatus AS slotStatus,"
				+ "SS.slot_cardNum AS cardNum,"
				+ "SS.is_slot_illegal AS isSlotIllegal,"
				+ "SS.lockStatus AS lockStatus,"
				+ "SS.is_childDevice_register AS isChildDeviceRegister,"
				+ "SS.is_childDevice_online AS isChildDeviceOnline,"
				+ "SS.is_serviceLight_on AS isServiceLightOn,"
				+ "SS.is_roomLight_on AS isRoomLightOn "
				+ "FROM RoomList AS R, SlotStatus AS SS,RoomSoltList AS RS "
				+ "WHERE R.HotelList_idHotelList= (SELECT H.idHotelList FROM HotelList AS H WHERE H.hotelID='"+hotelCode+"') "
						+ "AND RS.soltList_idsoltList = SS.idslotList "
						+ "AND RS.RoomList_idRoomList = R.idRoomList");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("roomCode")
				.addScalar("roomNum")
				.addScalar("roomFloor")
				.addScalar("slotStatus")
				.addScalar("cardNum")
				.addScalar("isSlotIllegal")
				.addScalar("lockStatus")
				.addScalar("isChildDeviceRegister")
				.addScalar("isChildDeviceOnline")
				.addScalar("isServiceLightOn")
				.addScalar("isRoomLightOn")
				.setResultTransformer(Transformers.aliasToBean(RoomStatusDto.class)).list();
	}

	@Override
	public RoomStatusDto getRoomStatusByRoomCode(String roomCode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "H.hotelID AS hotelCode,"
				+ "R.RoomID AS roomCode,"
				+ "R.roomNum AS roomNum,"
				+ "R.floor AS roomFloor,"
				+ "SS.slotStatus AS slotStatus,"
				+ "SS.slot_cardNum AS cardNum,"
				+ "SS.is_slot_illegal AS isSlotIllegal,"
				+ "SS.lockStatus AS lockStatus,"
				+ "SS.is_childDevice_register AS isChildDeviceRegister,"
				+ "SS.is_childDevice_online AS isChildDeviceOnline,"
				+ "SS.is_serviceLight_on AS isServiceLightOn,"
				+ "SS.is_roomLight_on AS isRoomLightOn "
				+ "FROM RoomList AS R, SlotStatus AS SS,RoomSoltList AS RS,HotelList AS H "
				+ "WHERE R.RoomID='"+roomCode+"'"
				+ "AND RS.soltList_idsoltList = SS.idslotList "
				+ "AND RS.RoomList_idRoomList = R.idRoomList "
				+ "AND H.idHotelList = R.HotelList_idHotelList");
		return (RoomStatusDto) sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("roomCode")
				.addScalar("roomNum")
				.addScalar("roomFloor")
				.addScalar("slotStatus")
				.addScalar("cardNum")
				.addScalar("isSlotIllegal")
				.addScalar("lockStatus")
				.addScalar("isChildDeviceRegister")
				.addScalar("isChildDeviceOnline")
				.addScalar("isServiceLightOn")
				.addScalar("isRoomLightOn")
				.setResultTransformer(Transformers.aliasToBean(RoomStatusDto.class)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructionsHistoryDto> getInstructionsHistory(String beginId, String endId) {
		StringBuffer sql = new StringBuffer();
		int begin = Integer.valueOf(beginId);
		int end = Integer.valueOf(endId);
		StringBuffer idBuf = new StringBuffer();
		for (int i = begin; i < end+1; i++) {
			idBuf.append("'"+i+"',");
		}
		idBuf.deleteCharAt(idBuf.length()-1);
		sql.append("SELECT "
				+ "P.instructions_content AS instructionsContent, "
				+ "P.sendTime AS sendTime "
				+ "FROM "
				+ "PMSInstru AS P "
				+ "WHERE "
				+ "id "
				+ "in("+idBuf.toString()+")");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("instructionsContent")
				.addScalar("sendTime")
				.setResultTransformer(Transformers.aliasToBean(InstructionsHistoryDto.class)).list();
	}
	

}
