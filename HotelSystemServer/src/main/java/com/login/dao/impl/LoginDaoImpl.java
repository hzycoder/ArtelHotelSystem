package com.login.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.pojo.LoginUserList;
import com.login.dao.LoginDao;
import com.login.dto.LoginDto;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	LoginUserList user;

//	@Override
//	public List<?> getUserInfo(Integer id) {//
//		StringBuffer sql = new StringBuffer("");
//		sql.append("SELECT " 
//		+ "SU.ID, " 
//		+ "SU.USER_CODE, "
//		+ "SU.USER_ACCOUNT, "
//		+ "SU.USER_PASSWORD, "
//		+ "SU.PASSWORD_SALT, "
//		+ "SU.USER_SEX, "
//		+ "SU.USERNAME, "
//		+ "SU.USER_PHONE, "
//		+ "SU.USER_POSITION, "
//		+ "SU.USER_PERMISSION, "
//		+ "SU.CREATOR, "
//		+ "SU.CREATE_TIME, "
//		+ "SU.LAST_TIME, "
//		+ "SU.RETRY_COUNT "
//		
//		+ "FROM SYS_USER SU "
//		+ "WHERE 1=1 "
//		+ "AND SU.ID = '"+id+"'"
//		);
//		return sessionFactory.getCurrentSession().createSQLQuery(sql.toString())
//				.list();
//	}

	@Override
	public Integer existUser(LoginDto loginDto) {
		return (Integer) sessionFactory.getCurrentSession().createQuery("SELECT id FROM LoginUserList WHERE account = ?")
		.setParameter(0, loginDto.getAccount()).uniqueResult();
		
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
	public LoginUserList getUser(Integer id) {
		return (LoginUserList) sessionFactory.getCurrentSession().createQuery("FROM LoginUserList WHERE id = ?")
				.setParameter(0, id).uniqueResult();
	}


}
