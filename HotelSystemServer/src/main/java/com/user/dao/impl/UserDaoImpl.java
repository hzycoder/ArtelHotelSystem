package com.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.LoginUserList;
import com.user.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	LoginUserList user;

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserList> getAllUser() {
		System.out.println("getDao");
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

}
