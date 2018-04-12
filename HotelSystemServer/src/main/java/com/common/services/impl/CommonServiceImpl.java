package com.common.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.services.CommonService;

@Transactional
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	CommonDao commonDao;

	@Override
	public boolean verifyAccount(String account) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT" 
				+ " account" 
				+ " FROM" 
				+ " LoginUserList" 
				+ " WHERE" 
				+ " account" 
				+ " = " 
				+ "'" + account
				+ "'");
		List<?> list = commonDao.getOneLine(sql.toString());
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verifyHotelId(String hotelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT" 
				+ " hotelId" 
				+ " FROM" 
				+ " HotelList" 
				+ " WHERE" 
				+ " hotelId" 
				+ " = " 
				+ "'" + hotelId
				+ "'");
		List<?> list = commonDao.getOneLine(sql.toString());
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
