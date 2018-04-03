package com.common.dao;

import java.util.List;

public interface CommonDao {
	public Integer getCount(String sql);
	public List<?> getOneLine(String sql);
}
