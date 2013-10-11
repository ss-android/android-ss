package com.activity.custome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.custome.CustomeIndexActivity.UIHandler;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.ListViewSearch;
import com.view.LoadingDilog;
import com.view.SearchView;
import com.view.HeadBar.BtnType;

public class CustomeDetailActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;
	private LoadingDilog lodingDilog;
	private SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;

	private ListView lvSearch;
	private SearchCustomeAdapter searchAdapter;
	private View btnAddHome;
	private View btnDeleteHome;
	private View Layout_Home;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_select_custome_detail);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("编辑客户");
		headBar.setRightType(BtnType.btn);
		headBar.setBtnRightText("完成");
		headBar.setWidgetClickListener(this);
		btnAddHome = (View) findViewById(R.id.Btn_ADD_HOME);
		btnDeleteHome = (View) findViewById(R.id.Btn_DELETE_HOME);
		btnAddHome.setOnClickListener(this);
		btnDeleteHome.setOnClickListener(this);
		Layout_Home = (View) findViewById(R.id.Layout_Home);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		case R.id.Btn_ADD_HOME:
			Layout_Home.setVisibility(View.VISIBLE);
			break;
		case R.id.Btn_DELETE_HOME:
			Layout_Home.setVisibility(View.GONE);
			break;
		}

	}

}
