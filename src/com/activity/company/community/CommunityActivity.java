package com.activity.company.community;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.community.BannnerAdapter;
import com.http.company.CommunityApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.BannerIndicator;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class CommunityActivity extends CommonActivity implements
		OnClickListener {
	private LocalInfoDao localInfoDao;
	private ViewPager viewPager;
	private BannerIndicator bannerIndicator;
	private BannnerAdapter bannnerAdapter;
	private static final int MSG_UPDATE = 1;
	private UiHandler uiHandler;
	CommunityAdapter communityAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_community);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.community);

		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_community);
		communityAdapter = new CommunityAdapter(this);
		communityAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(communityAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_community));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		initWidget();

		lvAnnouncement.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("debug", "onClick");
			}
		});
		uiHandler = new UiHandler();
		update();
	}

	public void initWidget() {
		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Banner);
		bannerIndicator = (BannerIndicator) findViewById(R.id.Indicator);
		List<community> community = new ArrayList<community>();

		for (int i = 0; i < 4; i++) {
			community n = new community();
			n.setCommnuityTitile("公益事业" + i);
			community.add(n);
		}
		bannnerAdapter = new BannnerAdapter(this, community);
		viewPager.setAdapter(bannnerAdapter);

		bannerIndicator.setCount(community.size());

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int current) {
				bannerIndicator.setCurrent(current);
				bannerIndicator.setTvTitle(bannnerAdapter.getCommunity()
						.get(current).getCommnuityTitile());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * 网络更新数据
	 */
	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = CommunityApi.getCommunity();
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
				communityAdapter.setLocalInfos(localInfos);
				communityAdapter.notifyDataSetChanged();
				localInfoDao.deleteByType(InfoType.community);
				localInfoDao.batchInsert(localInfos);
				break;

			default:
				break;
			}

		}
	}
}