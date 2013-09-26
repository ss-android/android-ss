package com.activity;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.application.CommonApplication;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sansheng.db.OrmDateBaseHelper;

//push  ok
public class CommonActivity extends SherlockFragmentActivity {
	public static int THEME = R.style.Theme_Sherlock_Light;
	TextView tvTitle;
	private CommonApplication comApp;
	private OrmDateBaseHelper ormDateBaseHelper;
	public ImageLoader imageLoader = ImageLoader.getInstance();
	public DisplayImageOptions options;

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
		actionBar = getSupportActionBar();
		actionBar.hide();

		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.shop_big)
				.showImageForEmptyUri(R.drawable.shop_big)
				.showImageOnFail(R.drawable.shop_big).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20))
				.build();

	}

	public BaseRequest createRequest(int action) {
		BaseRequest commonRequest = new BaseRequest();
		Map<String, String> params = new HashMap<String, String>();
		commonRequest.setAction(action);
		commonRequest.setParams(params);
		return commonRequest;
	}

	public BaseRequest createRequestWithUserId(int action) {
		BaseRequest commonRequest = new BaseRequest();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", getUserId());
		commonRequest.setAction(action);
		commonRequest.setParams(params);
		return commonRequest;
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

	public void refresh(ViewCommonResponse viewCommonResponse) {
		int action = viewCommonResponse.getAction();
		switch (action) {
		// 获取appcode请求结果不在此处理

		}

		int code = viewCommonResponse.getHttpCode();
		System.out.println("HttpCode:" + code);
		switch (code) {
		case 200:
			break;
		case 404:
		case 500:
			showDataLoadingErrToast();
			break;
		case 544:
			showDataLoadingErrToast("加载超时!");
			break;
		case 545:
			Log.i("TAG", "canel request");
			break;
		default:
			showToast("未知错误！");
			break;
		}
	}

	// 显示数据加载失败土司
	protected void showDataLoadingErrToast() {
		Toast.makeText(this, "数据载入失败", Toast.LENGTH_SHORT).show();
	}

	// 显示数据加载失败土司
	protected void showDataLoadingErrToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	// 土司提示
	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public String getUserId() {
		return "H7Mud3IiaWjWqdL4J4qEJA==";
	}
}
