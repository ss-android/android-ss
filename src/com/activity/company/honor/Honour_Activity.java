package com.activity.company.honor;

import java.util.List;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class Honour_Activity extends CommonActivity implements OnClickListener {
	private ExpandableListView elistview = null;
	private HonourAdapter adapter = null;
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(android.os.Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_honor);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;
		List<LocalInfo> localInfosTitle = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.honor);
		localInfosTitle = localInfoDao.getLoclInfosByType(InfoType.honor);

		this.elistview = (ExpandableListView) findViewById(R.id.Elv_HonorYear);
		this.adapter = new HonourAdapter(this);
		adapter.setLocalInfos(localInfos);
		adapter.setLocalInfosTitle(localInfosTitle);
		this.elistview.setAdapter(adapter);
		int honorCount = adapter.getGroupCount();
		for (int i = 0; i < honorCount; i++) {
			elistview.expandGroup(i);
		}
		elistview.setGroupIndicator(null);
		elistview.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_honor));
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
