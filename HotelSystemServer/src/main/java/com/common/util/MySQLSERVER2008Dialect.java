package com.common.util;

import org.hibernate.dialect.SQLServer2008Dialect;

public class MySQLSERVER2008Dialect extends SQLServer2008Dialect {
	public MySQLSERVER2008Dialect() {
		super();
		registerHibernateType(-9, "string");
	}
	
}
