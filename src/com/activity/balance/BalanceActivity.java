package com.activity.balance;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.cosmetic.CosmeticFragment;
import com.activity.shop.home.HealthFragment;
import com.activity.shop.nurse.NurseFragment;
import com.lekoko.sansheng.R;
import com.sansheng.model.Balance;
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.TabController;
import com.view.HeadBar.BtnType;
import com.view.TabController.TabListenner;

public class BalanceActivity extends CommonActivity implements OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;
	public static List<Balance> bList;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_balance);
		HeadBar headBar = (HeadBar) findViewById(R.id.HeadBar);

		headBar.setTitle("报单查询");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_head_add);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();

		BtnTab Btn_querytype0 = (BtnTab) findViewById(R.id.Btn_querytype0);
		BtnTab Btn_querytype1 = (BtnTab) findViewById(R.id.Btn_querytype1);
		BtnTab Btn_querytype2 = (BtnTab) findViewById(R.id.Btn_querytype2);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), Balance_type0Activity.class, null);
		tabsAdapter.addTab(actionBar.newTab(), NurseFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), HealthFragment.class, null);

		tabController.addTab(Btn_querytype0);
		tabController.addTab(Btn_querytype1);
		tabController.addTab(Btn_querytype2);
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
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			i = new Intent(this, CompanyIndexActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}

}
