package com.activity.shop.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.cosmetic.CosmeticFragment;
import com.activity.shop.home.HealthFragment;
import com.activity.shop.life.LifeFragment;
import com.activity.shop.nurse.NurseFragment;
import com.lekoko.sansheng.R;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.TabController.TabListenner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-30 下午8:30:49 declare:
 */
public class ShopDetailActivity extends CommonActivity implements
		OnClickListener {
	private TabController tabController;
	public static ViewPager viewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		setContentView(R.layout.activity_shop_detail);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("零售报单");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();
		BtnTab tabInfo = (BtnTab) findViewById(R.id.Btn_Info);
		BtnTab tabDetail = (BtnTab) findViewById(R.id.Btn_Detail);
		BtnTab tabEvaluation = (BtnTab) findViewById(R.id.Btn_Evaluation);
		tabController.addTab(tabInfo);
		tabController.addTab(tabDetail);
		tabController.addTab(tabEvaluation);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);

		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), ShopInfoFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), ShopIntroduceFragment.class,
				null);
		tabsAdapter.addTab(actionBar.newTab(), ShopEvaulationFragment.class,
				null);

		tabController.setTabListenner(new TabListenner() {

			@Override
			public void onTabClick(final int i) {
				Log.e("debug", "index" + i);
				viewPager.setCurrentItem(i);
			}

		});

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int item) {
				viewPager.setCurrentItem(item);
				tabController.selected(item);
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
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		default:
			break;
		}

	}
}
