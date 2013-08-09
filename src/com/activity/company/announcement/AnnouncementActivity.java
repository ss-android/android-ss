package com.activity.company.announcement;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.DateUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class AnnouncementActivity extends CommonActivity implements
		android.view.View.OnClickListener {
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_announcement);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.announce);

		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_Announcement);
		AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(this);
		announcementAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(announcementAdapter);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.latest_news));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			i = new Intent(this, CompanyIndexActivity.class);
			startActivity(i);
			finish();
			break;

		default:
			break;
		}
	}
}
