package com.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.application.CommonApplication;
import com.example.sansheng.R;
import com.sansheng.db.OrmDateBaseHelper;

//push  ok
public class CommonActivity extends SherlockFragmentActivity {
	public static int THEME = R.style.Theme_Sherlock_Light_NoActionBar;
	TextView tvTitle;
	private CommonApplication comApp;
	private OrmDateBaseHelper ormDateBaseHelper;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setTheme(THEME);
		ActionBar actionBar = getSupportActionBar();
		// tvTitle = (TextView) getSupportActionBar().getCustomView()
		// .findViewById(R.id.Tv_Title);
		comApp = (CommonApplication) getApplication();
		ormDateBaseHelper = comApp.getOrmDateBaseHelper();
	}

	public String getStr(int strId) {
		String string = getResources().getString(strId);
		return string;
	}

	public void setTitle(String title) {
		tvTitle.setText(title);
	}

	public CommonApplication getComApp() {
		return comApp;
	}

	public OrmDateBaseHelper getOrmDateBaseHelper() {
		return ormDateBaseHelper;
	}

}
