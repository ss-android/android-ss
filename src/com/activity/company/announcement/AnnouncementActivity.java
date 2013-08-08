package com.activity.company.announcement;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.util.DateUtil;

public class AnnouncementActivity extends CommonActivity {
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_announcement);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();

		List<LocalInfo> localInfos = null;

		try {
			localInfos = localInfoDao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_Announcement);
		AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(this);
		announcementAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(announcementAdapter);
	}
}
