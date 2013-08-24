package com.sansheng.db;

import java.sql.SQLException;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.dao.interfaze.LogisticsDao;
import com.sansheng.dao.interfaze.PlanDao;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Logistics;
import com.sansheng.model.Plan;
import com.sansheng.model.Schedule;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.DateUtil;

public class OrmDateBaseHelper extends SQLiteOpenHelper {

	private static ScheduleDao scheduleDao;

	private static LogisticsDao logisticsDao;

	private static PlanDao planDao;

	private static LocalInfoDao localInfoDao;

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

			TableUtils.createTableIfNotExists(connectionSource, Plan.class);

			TableUtils
					.createTableIfNotExists(connectionSource, LocalInfo.class);

			scheduleDao = DaoManager
					.createDao(connectionSource, Schedule.class);

			logisticsDao = DaoManager.createDao(connectionSource,
					Logistics.class);
			planDao = DaoManager.createDao(connectionSource, Plan.class);

			localInfoDao = DaoManager.createDao(connectionSource,
					LocalInfo.class);
			initData();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initData() {
		try {
			if (localInfoDao.queryForAll().size() == 0) {
				initAnnounce();
				initNews();
				initHistory();
				initIntroduce();
				initSale();
				initBrands();
				initCulture();
				initChariman();
				initIndustry();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initAnnounce() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("公告标题公告标题");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("充满泰式风情的低矮建筑公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容");
			localInfo.setType(InfoType.announce);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initNews() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("新闻标题");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo.setContent("新闻内容新闻内容新闻内容新闻内容新闻内容");
			localInfo.setType(InfoType.news);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initIntroduce() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("健康家生活，自然生活力");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文");
			localInfo.setType(InfoType.introduce);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initHistory() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("总投资2.5亿元人民币的三生会议中心正式落成启用");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文");
			localInfo.setType(InfoType.history);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initSale() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("促销");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo.setType(InfoType.sales);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initBrands() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("品牌标题");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！品牌内容！");
			localInfo.setType(InfoType.brand);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initCulture() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("三生文化");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字");
			localInfo.setType(InfoType.culture);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initChariman() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("董事长寄语标题");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字");
			localInfo.setType(InfoType.welfare);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initIndustry() {
		for (int i = 0; i < 10; i++) {
			LocalInfo localInfo = new LocalInfo();
			localInfo.setTitle("三生全球生产研发基地");
			localInfo.setData(DateUtil.Format(new Date()));
			localInfo
					.setContent("文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内");
			localInfo.setType(InfoType.industry);
			try {
				localInfoDao.create(localInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public static PlanDao getPlanDao() {
		return planDao;
	}

	public LocalInfoDao getLocalInfoDao() {
		return localInfoDao;
	}

}
