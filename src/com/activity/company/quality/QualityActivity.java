package com.activity.company.quality;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.introduce.IntroduceAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class QualityActivity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;

	Drawable dra1;
	Drawable dra2;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_quality);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.announce);

		dra1 = getResources().getDrawable(R.drawable.arrow1);
		dra2 = getResources().getDrawable(R.drawable.arrow2);
		dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
		dra2.setBounds(0, 0, dra2.getMinimumWidth(), dra2.getMinimumHeight());
		
		ExpandableListView lvQuality = (ExpandableListView) findViewById(R.id.Lv_quality);
		QualityAdapter qualityAdapter = new QualityAdapter(this);
		qualityAdapter.setLocalInfos(localInfos);
		qualityAdapter.setDraw(dra2);
		lvQuality.setGroupIndicator(null);
		lvQuality.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				TextView tv = (TextView) v.findViewById(R.id.Tv_QualityContent);
				Button btn = (Button) v.findViewById(R.id.IvBtn_ContentThree);
				if (tv.getVisibility() > 0) {
					tv.setVisibility(View.VISIBLE);
					// tv.setHeight(pxNum);
					btn.setCompoundDrawables(null, null, dra2, null);
					btn.setText(null);
				} else {
					tv.setVisibility(View.GONE);
					btn.setCompoundDrawables(null, null, dra1, null);
					btn.setText("点击展开  ");
				}
				return false;
			}

		});

		lvQuality.setAdapter(qualityAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_quality));
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
