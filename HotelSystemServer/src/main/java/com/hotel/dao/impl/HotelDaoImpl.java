package com.hotel.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.HotelList;
import com.common.pojo.LoginUserList;
import com.common.pojo.RoomList;
import com.hotel.dao.HotelDao;

@Repository
public class HotelDaoImpl implements HotelDao {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	LoginUserList user;

	// 超管查询
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotels() {
		return sessionFactory.getCurrentSession().createQuery("FROM HotelList WHERE hotelManager > 0").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotels(String userID) {
		return sessionFactory.getCurrentSession().createQuery("FROM HotelList WHERE hotelManager = ?")
				.setParameter(0, userID).list();
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
				.createQuery("FROM HotelList WHERE hotelName like :hotelName AND address like :address AND hotelManager > 0")
				.setString("hotelName", "%" + hotelName + "%").setString("address", "%" + hotelAddress + "%").list();
	}

	@Override
	public int delHotel(String hotelID) {
		int result = sessionFactory.getCurrentSession()
				.createSQLQuery("UPDATE HotelList SET hotelManager = -1*hotelManager WHERE idHotelList = '"+hotelID+"'").executeUpdate();
		return result;
	}

	@Override
	public void addHotel(HotelList hotelList) {
		sessionFactory.getCurrentSession().saveOrUpdate(hotelList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomList> getRooms(String hotelId) {
		return sessionFactory.getCurrentSession().createQuery("FROM RoomList WHERE hotelId = ?")
				.setParameter(0, Integer.parseInt(hotelId)).list();
	}

	@Override
	public Integer getRoomsCount(String hotelId) {
		long temp = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM RoomList WHERE hotelId = ?")
				.setParameter(0, Integer.parseInt(hotelId)).uniqueResult();
		return (int) temp;
	}

	@Override
	public int delRoom(String roomID) {
		int result = sessionFactory.getCurrentSession().createQuery("DELETE FROM RoomList WHERE idRoomList = ?")
				.setParameter(0, Integer.parseInt(roomID)).executeUpdate();
		return result;
	}

	@Override
	public void addRoom(RoomList roomList) {
		sessionFactory.getCurrentSession().saveOrUpdate(roomList);
	}

	@Override
	public Integer getRoomCountByHotelID(int hotelID) {
		Object object = sessionFactory.getCurrentSession()
				.createQuery("SELECT MAX(idRoomList) FROM RoomList WHERE hotelId = ?").setParameter(0, hotelID)
				.uniqueResult();
		int temp = object == null ? 0 : (int) object;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTypeOfHotel(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "A.deviceCount "
				+ "FROM "
				+ "AgentList A "
				+ "WHERE "
				+ "A.idAgentList "
				+ "IN "
				+ "(SELECT "
				+ "HA.AgentList_idAgentList "
				+ "FROM "
				+ "HotelAgentList HA "
				+ "WHERE "
				+ "HA.HotelList_idHotelList "
				+ "="
				+ "'"
				+ hotelId
				+ "')");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).list();
		
	}

	@Override
	public Integer getMaxHotelId() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "max(H.idHotelList) "
				+ "FROM HotelList "
				+ "AS "
				+ "H");
		return (Integer) sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	@Override
	public List<HotelList> getHotelByHotelName(String hotelName) {
		@SuppressWarnings("unchecked")
		List<HotelList> hotelList = sessionFactory.getCurrentSession().createQuery("FROM HotelList WHERE hotelName = :hotelName")
				.setString("hotelName", hotelName).list();
		return hotelList;
	}

}
