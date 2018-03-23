package com.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.LoginUserList;
import com.user.dao.UserDao;
import com.user.dto.LoginDto;

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
	public Integer getUserCount() {
		long temp = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM LoginUserList").uniqueResult();
		return (int) temp;
	}
	@Override
	public Integer existUser(LoginDto loginDto) {
		return (Integer) sessionFactory.getCurrentSession().createQuery("SELECT id FROM LoginUserList WHERE account = ?")
		.setParameter(0, loginDto.getAccount()).uniqueResult();
		
	}

	@Override
	public LoginUserList getUser(Integer id) {
		return (LoginUserList) sessionFactory.getCurrentSession().createQuery("FROM LoginUserList WHERE id = ?")
				.setParameter(0, id).uniqueResult();
	}

	@Override
	public List<LoginUserList> getUserList(int maxResult, int firstResult, int permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUserCount(int permission) {
		// TODO Auto-generated method stub
		return null;
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

	@SuppressWarnings("unchecked")
	@Override
	public LoginUserList getUserByUserID(String userID) {
		return (LoginUserList) sessionFactory.getCurrentSession().createQuery("FROM LoginUserList WHERE idUserList = ?").setParameter(0, Integer.valueOf(userID)).uniqueResult();
	}
}
