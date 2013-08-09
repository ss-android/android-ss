package com.activity.company.introduce;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.announcement.AnnouncementAdapter;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午1:39:55 declare:
 */
public class IntroduceAcitity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_introduce);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.announce);

		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_Announcement);
		IntroduceAdapter announcementAdapter = new IntroduceAdapter(this);
		announcementAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(announcementAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_about));
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
