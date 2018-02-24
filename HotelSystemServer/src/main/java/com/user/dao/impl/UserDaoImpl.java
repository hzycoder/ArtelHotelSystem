package com.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.SysUser;
import com.user.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	SysUser user;

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> getAllUser() {
		System.out.println("getDao");
		String hql = "FROM SysUser";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<SysUser> userList = query.list();
		return userList;
		
	}

	@Override
	public Integer getUserCount() {
		long temp = (Long) sessionFactory.getCurrentSession()
				.createQuery("SELECT COUNT(*) FROM SysUser").uniqueResult();
		return (int) temp;
	}

}
