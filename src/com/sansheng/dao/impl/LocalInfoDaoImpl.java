package com.sansheng.dao.impl;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Logistics;

public class LocalInfoDaoImpl extends BaseDaoImpl<LocalInfo, String> implements
		LocalInfoDao {
	public LocalInfoDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, LocalInfo.class);
	}
}
