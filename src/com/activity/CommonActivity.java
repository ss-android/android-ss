package com.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.sansheng.model.Room;

//push  ok
public class CommonActivity extends SherlockFragmentActivity {
	public static int THEME = R.style.Theme_Sherlock_Light;
	TextView tvTitle;
	private CommonApplication comApp;
	private OrmDateBaseHelper ormDateBaseHelper;
	public ImageLoader imageLoader = ImageLoader.getInstance();
	public DisplayImageOptions options;

	SharedPreferences userInfo;

	public static final String SHAREDPREFERENCES_NAME = "base_info";
	public static final String SHOP_ID = "SHOP_ID";
	public static final String SHOP_USER_ID = "SHOP_USER_ID";
	public static final String SUM_PRICE = "SUM_PRICE";
	public static final String SUM_PV = "SUM_PV";

	public static final String STORE_ID = "STORE_ID";
	public static final String BIAN_HAP = "BIANHAO";

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
		userInfo = getSharedPreferences(SHAREDPREFERENCES_NAME, 0);

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

	public String getUserName() {
		return "公司";
	}

	public void saveRoom(Room room) {
		Editor editor = userInfo.edit();
		editor.putString(SHOP_ID, room.getShopid());
		editor.putString(SHOP_USER_ID, room.getShopuserid());
		editor.commit();
	}

	public Room getRoom() {
		Room room = new Room();
		String shopId = userInfo.getString(SHOP_ID, "");
		String shopUserId = userInfo.getString(SHOP_ID, "");
		room.setShopid(shopId);
		room.setShopuserid(shopUserId);
		return room;
	}

	public void saveSumPrice(String sumPrice) {
		Editor editor = userInfo.edit();
		editor.putString(SUM_PRICE, sumPrice);
		editor.commit();
	}

	public String getSumPrice() {
		String sumPrice = userInfo.getString(SUM_PRICE, "0");
		return sumPrice;
	}

	public void saveSumPv(String sumPrice) {
		Editor editor = userInfo.edit();
		editor.putString(SUM_PV, sumPrice);
		editor.commit();
	}

	public String getSumPv() {
		String sumPrice = userInfo.getString(SUM_PV, "0");
		return sumPrice;
	}

	public void saveBainHao(String bianhao) {
		Editor editor = userInfo.edit();
		editor.putString(BIAN_HAP, bianhao);
		editor.commit();
	}

	public void saveStoreId(String storeId) {
		Editor editor = userInfo.edit();
		editor.putString(STORE_ID, storeId);
		editor.commit();
	}

	public String getStoreId() {
		String sumPrice = userInfo.getString(STORE_ID, "0");
		return sumPrice;
	}

	public String getBianHao() {
		String sumPrice = userInfo.getString(BIAN_HAP, "0");
		return sumPrice;
	}

}
