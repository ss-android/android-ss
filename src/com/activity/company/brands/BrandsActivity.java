package com.activity.company.brands;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.http.company.BrandApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class BrandsActivity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;
	BrandsAdapter brandsAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_brands);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;
		localInfos = localInfoDao.getLoclInfosByType(InfoType.brand);
		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_My_Brands);
		brandsAdapter = new BrandsAdapter(this);
		brandsAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(brandsAdapter);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.brand_indroduc));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		uiHandler = new UiHandler();
		update();
	}

	/**
	 * 网络更新数据
	 */
	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = BrandApi.getBrands();
				Message msg = new Message();
				msg.what = MSG_UPDATE;
				msg.obj = localInfos;
				uiHandler.sendMessage(msg);
			};
		}.start();
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

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_UPDATE:
				List<LocalInfo> localInfos = (List<LocalInfo>) msg.obj;
				brandsAdapter.setLocalInfos(localInfos);
				brandsAdapter.notifyDataSetChanged();
				localInfoDao.deleteByType(InfoType.brand);
				localInfoDao.batchInsert(localInfos);
				break;

			default:
				break;
			}
		}
	}
}