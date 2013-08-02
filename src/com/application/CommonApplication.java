package com.application;

import android.app.Application;
import android.util.Log;

import com.sansheng.db.OrmDateBaseHelper;

public class CommonApplication extends Application {
	private OrmDateBaseHelper ormDateBaseHelper;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("debug", "commonApplication  is  oncreate");
		ormDateBaseHelper = new OrmDateBaseHelper(this, "cloud_contact", null,
				1);

	}

	public OrmDateBaseHelper getOrmDateBaseHelper() {
		return ormDateBaseHelper;
	}

	public void setOrmDateBaseHelper(OrmDateBaseHelper ormDateBaseHelper) {
		this.ormDateBaseHelper = ormDateBaseHelper;
	}

}
