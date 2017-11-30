package com.login.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.SysUser;
import com.login.dao.LoginDao;
import com.login.dto.LoginDto;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	SysUser user;
	
	@Override
	public SysUser getUser(LoginDto loginDto) {
		return (SysUser) sessionFactory.getCurrentSession().createQuery("from SysUser where userAccount=?")
				.setParameter(0, loginDto.getAccount()).uniqueResult();
	}

	@Override
	public boolean existUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SysUser> getUserList(int maxResult, int firstResult, int permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUserCount(int permission) {
		// TODO Auto-generated method stub
		return null;
	}

}
