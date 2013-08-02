package com.sansheng.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sansheng.dao.interfaze.LogisticsDao;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Logistics;
import com.sansheng.model.Schedule;

public class OrmDateBaseHelper extends SQLiteOpenHelper {

	private static ScheduleDao scheduleDao;

	private static LogisticsDao logisticsDao;

	public OrmDateBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		initScheme();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("debug", "onCreate");

	}

	public void initScheme() {

		ConnectionSource connectionSource = new AndroidConnectionSource(this);
		try {
			TableUtils.createTableIfNotExists(connectionSource, Schedule.class);
			TableUtils
					.createTableIfNotExists(connectionSource, Logistics.class);

			scheduleDao = DaoManager
					.createDao(connectionSource, Schedule.class);

			logisticsDao = DaoManager.createDao(connectionSource,
					Logistics.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.e("debug", "onCreate");
	}

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		OrmDateBaseHelper.scheduleDao = scheduleDao;
	}

	public static LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public static void setLogisticsDao(LogisticsDao logisticsDao) {
		OrmDateBaseHelper.logisticsDao = logisticsDao;
	}

}
