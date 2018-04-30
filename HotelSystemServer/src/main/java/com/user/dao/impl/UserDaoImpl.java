package com.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.LoginUserList;
import com.user.dao.UserDao;
import com.user.dto.LoginDto;
import com.user.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	LoginUserList user;

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserList> getAllUser() {
		String hql = "FROM LoginUserList";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<LoginUserList> userList = query.list();
		return userList;
		
	}

	@Override
	public Integer existUser(LoginDto loginDto) {
		return (Integer) sessionFactory.getCurrentSession().createQuery("SELECT id FROM LoginUserList WHERE account = ?")
		.setParameter(0, loginDto.getAccount()).uniqueResult();
	}

	@Override
	public LoginUserList getUserById(Integer id) {
		LoginUserList user = (LoginUserList) sessionFactory.getCurrentSession()
				.createQuery("FROM LoginUserList WHERE id = ?").setParameter(0, id).uniqueResult();
		return user;
	}

	@Override
	public Integer modifyUser(String userName, String userPhone, String position, String userID) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE "
				+ "LoginUserList"
				+ " SET "
				+ "position = '"
				+ position
				+ "',userName = '"
				+ userName
				+ "',userPhone = '"
				+ userPhone
				+ "' WHERE "
				+ "idUserList = "
				+ "'"
				+ userID
				+ "'");
		Query query =  sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}

	@Override
	public String getPassByUserId(String userID) {
		return (String) sessionFactory.getCurrentSession().createQuery("SELECT password FROM LoginUserList WHERE idUserList = ?").setParameter(0, Integer.valueOf(userID)).uniqueResult();
	}

	@Override
	public void register(LoginUserList user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> getUnbindedUser() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT"
				+ " L.idUserList idUserList"
				+ ",L.account account"
				+ ",L.userName userName"
				+ ",L.userId userId"
				+ ",L.userPhone userPhone"
				+ ",L.position position"
				+ ",L.permission permission"
				+ ",L.creator creator"
				+ ",L.createTime createTime"
				+ " FROM"
				+ " LoginUserList"
				+ " AS"
				+ " L"
				+ " WHERE"
				+ " L.idUserList"
				+ " NOT IN"
				+ " ("
				+ "SELECT"
				+ " H.hotelManager"
				+ " FROM"
				+ " HotelList"
				+ " AS"
				+ " H"
				+ " )");
		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
				.addScalar("idUserList").addScalar("account").addScalar("userName").addScalar("userId")
				.addScalar("userPhone").addScalar("position").addScalar("permission").addScalar("creator")
				.addScalar("createTime").setResultTransformer(Transformers.aliasToBean(UserDto.class)).list();
		
	}

	@Override
	public Integer modifyPass(String id, String newPass) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE " + "LoginUserList " + "SET " + "password = '" + newPass + "' WHERE " + "idUserList = '"
				+ id + "'");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.executeUpdate();
	}
}
