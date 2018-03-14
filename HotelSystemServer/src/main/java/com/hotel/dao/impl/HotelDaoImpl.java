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

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotels() {
		return sessionFactory.getCurrentSession().createQuery("FROM HotelList").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelList> gethotelsByConditions(String hotelName, String hotelAddress) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM HotelList WHERE hotelName like :hotelName and address like :address").setString("hotelName", "%"+hotelName+"%").setString("address", "%"+hotelAddress+"%").list();
	}

	@Override
	public Integer getHotelsCount(String sql) {
		long temp = (Long) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
		return (int) temp;
	}

	@Override
	public int delHotel(String hotelID) {
		int result = sessionFactory.getCurrentSession().createQuery("DELETE FROM HotelList WHERE idHotelList = ?")
				.setParameter(0, Integer.parseInt(hotelID)).executeUpdate();
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
		System.out.println(roomList.toString());
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

}
