package com.activity.company.cultural;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.introduce.IntroduceAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class CulturalActivity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_cultural);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.culture);

		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_cultural);
		IntroduceAdapter announcementAdapter = new IntroduceAdapter(this);
		announcementAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(announcementAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.sansheng_cultural));
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
