package com.activity.company.news;

import java.util.List;

import android.app.Activity;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.InfoDetailActivity;
import com.http.company.AnnouncementApi;
import com.http.company.NewsApi;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.util.ProgressDialogUtil;
import com.view.BannerIndicator;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-8 下午1:39:55 declare:
 */
public class NewsActivity extends CommonActivity implements OnClickListener {
	private ViewPager viewPager;
	private BannerIndicator bannerIndicator;
	private BannnerAdapter newsBannerAdapter;
	private static final int MSG_UPDATE = 1;
	private static final int MSG_UPDATE_BANNER = 2;
	private UiHandler uiHandler;
	List<LocalInfo> localInfos;
	NewsAdapter newsAdapter;
	private Activity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_news);
		activity = this;
		uiHandler = new UiHandler();
		ListView lvAnnouncement = (ListView) findViewById(R.id.Lv_Announcement);
		newsAdapter = new NewsAdapter(this);
		newsAdapter.setLocalInfos(localInfos);
		lvAnnouncement.setAdapter(newsAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_news));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		initWidget();

		lvAnnouncement.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				LocalInfo localInfo = newsAdapter.getLocalInfos().get(position);
				Log.e("debug", "url");
				Intent i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "新闻详情");
				i.putExtra(InfoDetailActivity.URL, localInfo.getUrl());
				startActivity(i);

			}
		});
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);

		update();
	}

	public void initWidget() {
		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Banner);
		bannerIndicator = (BannerIndicator) findViewById(R.id.Indicator);
		newsBannerAdapter = new BannnerAdapter(this, localInfos);
		newsBannerAdapter.activity = this;
		viewPager.setAdapter(newsBannerAdapter);

		bannerIndicator = (BannerIndicator) findViewById(R.id.Indicator);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int current) {
				bannerIndicator.setCurrent(current);
				bannerIndicator.setTvTitle(newsBannerAdapter.getNews()
						.get(current).getTitle());
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

	public void update() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				List<LocalInfo> localInfos = NewsApi.getNewss(0);
				Message msg = new Message();
				msg.what = MSG_UPDATE;
				msg.obj = localInfos;
				uiHandler.sendMessage(msg);
			};
		}.start();
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_UPDATE:
				ProgressDialogUtil.close();
				List<LocalInfo> localInfos = (List<LocalInfo>) msg.obj;
				newsAdapter.setLocalInfos(localInfos);
				newsAdapter.notifyDataSetChanged();
				bannerIndicator.setCount(localInfos.size());
				newsBannerAdapter.setNews(localInfos);
				break;

			case MSG_UPDATE_BANNER:
				break;
			}

		}
	}

}
