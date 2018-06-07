package com.hotel.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.common.pojo.RoomList;
import com.hotel.dao.HotelDao;
import com.hotel.dto.HotelDto;

@Repository
public class HotelDaoImpl implements HotelDao {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	LoginUserList user;

	// 超管查询
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDto> gethotels() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT H.idHotelList,"
				+ "H.hotelName,"
				+ "H.hotelID AS hotelId,"
				+ "H.country,"
				+ "H.province,"
				+ "H.city,"
				+ "H.address,"
				+ "H.hotelPhone,"
				+ "H.hotelManager,"
				+ "H.createTime,"
				+ "H.STATUS AS status,"
				+ "H.pmsId,"
				+ "L.idUserList,"
				+ "L.account,"
				+ "L.userName,"
				+ "L.userPhone,"
				+ "L.permission "
				+ "FROM HotelList AS H,"
				+ "LoginUserList AS L "
				+ "WHERE H.hotelManager = L.idUserList");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer(Transformers.aliasToBean(HotelDto.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDto> gethotels(String userID) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT H.idHotelList,"
				+ "H.hotelName,"
				+ "H.hotelID AS hotelId,"
				+ "H.country,"
				+ "H.province,"
				+ "H.city,"
				+ "H.address,"
				+ "H.hotelPhone,"
				+ "H.hotelManager,"
				+ "H.createTime,"
				+ "H.STATUS AS status,"
				+ "H.pmsId,"
				+ "L.idUserList,"
				+ "L.account,"
				+ "L.userName,"
				+ "L.userPhone,"
				+ "L.permission "
				+ "FROM HotelList AS H,"
				+ "LoginUserList AS L "
				+ "WHERE H.hotelManager = L.idUserList AND H.hotelManager = '"+userID+"'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer(Transformers.aliasToBean(HotelDto.class)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotelsByConditions(String hotelName, String hotelAddress, String userID) {
		return sessionFactory.getCurrentSession()
				.createQuery(
						"FROM HotelList WHERE hotelName like :hotelName and address like :address AND hotelManager = :hotelManager")
				.setString("hotelName", "%" + hotelName + "%").setString("address", "%" + hotelAddress + "%")
				.setString("hotelManager", userID).list();
	}

	// 超管查询
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotelsByConditions(String hotelName, String hotelAddress) {
		return sessionFactory.getCurrentSession()
				.createQuery(
						"FROM HotelList WHERE hotelName like :hotelName AND address like :address AND hotelManager > 0")
				.setString("hotelName", "%" + hotelName + "%").setString("address", "%" + hotelAddress + "%").list();
	}

	@Override
	public int delHotel(String hotelID) {
		int result = sessionFactory.getCurrentSession()
				.createSQLQuery(
						"UPDATE HotelList SET hotelManager = -1*hotelManager WHERE idHotelList = '" + hotelID + "'")
				.executeUpdate();
		return result;
	}

	@Override
	public int delRoom(String roomID) {
		int result = sessionFactory.getCurrentSession()
				.createSQLQuery(
						"UPDATE RoomList SET HotelList_idHotelList = -1*HotelList_idHotelList WHERE idRoomList = '" + roomID + "'")
				.executeUpdate();
		return result;
	}

	@Override
	public void addHotel(HotelList hotelList) {
		sessionFactory.getCurrentSession().saveOrUpdate(hotelList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomList> getRooms(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT R.idRoomList,R.RoomID AS roomId,R.roomNum,"
				+ "CASE WHEN R.soltNum IS NULL THEN '未绑定设备' ELSE R.soltNum END AS 'soltNum',"
				+ "R.floor,R.HotelList_idHotelList AS hotelId "
				+ "FROM RoomList AS R WHERE R.HotelList_idHotelList = '"+hotelId+"'");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(RoomList.class))
				.list();
	}
	
	/* (non-Javadoc)
	 * @see com.hotel.dao.HotelDao#getRooms(java.lang.String)
	 * 获取没绑定的房间
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomList> getUnbindedRooms(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT R.idRoomList,"
				+ "R.RoomID AS roomId,"
				+ "R.roomNum,"
				+ "R.soltNum,"
				+ "R.floor,"
				+ "R.HotelList_idHotelList AS hotelId "
				+ "FROM RoomList AS R "
				+ "WHERE "
				+ "R.HotelList_idHotelList='"+hotelId+"' "
				+ "AND "
				+ "R.idRoomList NOT IN "
				+ "(SELECT RS.RoomList_idRoomList FROM RoomSoltList AS RS)");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(RoomList.class))
				.list();
	}

	@Override
	public Integer getRoomsCount(String hotelId) {
		long temp = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM RoomList WHERE hotelId = ?")
				.setParameter(0, Integer.parseInt(hotelId)).uniqueResult();
		return (int) temp;
	}

	@Override
	public void addRoom(RoomList roomList) {
		sessionFactory.getCurrentSession().saveOrUpdate(roomList);
	}

	@Override
	public Integer getRoomCountByHotelID(int hotelID) {
		Integer count = (Integer) sessionFactory.getCurrentSession()
				.createSQLQuery("select count(*) from RoomList where HotelList_idHotelList = '"+hotelID+"' or HotelList_idHotelList = '-"+hotelID+"'")
				.uniqueResult();
		int temp = count == null ? 0 : (int) count;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTypeOfHotel(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "A.deviceCount " + "FROM " + "AgentList A " + "WHERE " + "A.idAgentList " + "IN "
				+ "(SELECT " + "HA.AgentList_idAgentList " + "FROM " + "HotelAgentList HA " + "WHERE "
				+ "HA.HotelList_idHotelList " + "=" + "'" + hotelId + "')");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).list();

	}

	@Override
	public Integer getMaxHotelId() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT " + "max(H.idHotelList) " + "FROM HotelList " + "AS " + "H");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	@Override
	public List<HotelList> getHotelByHotelName(String hotelName) {
		@SuppressWarnings("unchecked")
		List<HotelList> hotelList = sessionFactory.getCurrentSession()
				.createQuery("FROM HotelList WHERE hotelName = :hotelName AND hotelManager > 0")
				.setString("hotelName", hotelName).list();
		return hotelList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomList> verifyRoomNum(String roomNum, Integer hotelId) {
		List<RoomList> roomLists = sessionFactory.getCurrentSession()
				.createQuery("FROM RoomList WHERE roomNum = :roomNum AND hotelId = :hotelId")
				.setString("roomNum", roomNum).setInteger("hotelId", hotelId).list();
		return roomLists;
	}

}
