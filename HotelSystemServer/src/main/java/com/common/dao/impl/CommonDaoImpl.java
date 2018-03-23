package com.common.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.dao.CommonDao;

@Repository
public class CommonDaoImpl implements CommonDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Integer getCount(String sql) {
		long temp = (Long) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
		return (int) temp;
	}
}
