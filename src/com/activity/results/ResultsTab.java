package com.activity.results;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.cosmetic.CosmeticFragment;
import com.activity.shop.home.HealthFragment;
import com.activity.shop.life.LifeFragment;
import com.activity.shop.nurse.NurseFragment;
import com.lekoko.sansheng.R;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.IconButton;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.TabController.TabListenner;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class ResultsTab extends CommonActivity implements OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_results_tab);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);

		headBar.setTitle(getStr(R.string.results_query));
		headBar.setRightType(BtnType.empty);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();

		BtnTab tabBtn_MyResults = (BtnTab) findViewById(R.id.Btn_MyResults);
		BtnTab tabBtn_Retail = (BtnTab) findViewById(R.id.Btn_Retail);
		BtnTab tabBtn_Apo = (BtnTab) findViewById(R.id.Btn_Apo);
		BtnTab tabBtn_Fund = (BtnTab) findViewById(R.id.Btn_Fund);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), MyResults.class, null);
		tabsAdapter.addTab(actionBar.newTab(), NurseFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), HealthFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), CosmeticFragment.class, null);

		tabController.addTab(tabBtn_MyResults);
		tabController.addTab(tabBtn_Retail);
		tabController.addTab(tabBtn_Apo);
		tabController.addTab(tabBtn_Fund);
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
