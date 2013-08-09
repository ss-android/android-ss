package com.sansheng.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.sansheng.model.Logistics;

public class LocalInfoDaoImpl extends BaseDaoImpl<LocalInfo, String> implements
		LocalInfoDao {
	public LocalInfoDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, LocalInfo.class);
	}

	@Override
	public List<LocalInfo> getLoclInfosByType(InfoType infoType) {
		List<LocalInfo> localInfos = null;
		int type = LocalInfo.getInfoType(infoType);
		try {
			QueryBuilder<LocalInfo, String> qb = queryBuilder();
			qb.where().eq("type", type);
			PreparedQuery<LocalInfo> pq = qb.prepare();
			localInfos = query(pq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localInfos;
	}
}
