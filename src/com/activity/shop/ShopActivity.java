package com.activity.shop;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
import com.view.HeadBar.BtnType;
import com.view.IconButton;
import com.view.TabController;
import com.view.TabController.TabListenner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-25 下午9:09:43 declare:
 */
public class ShopActivity extends CommonActivity implements OnClickListener {
	private TabController tabController;
	private ViewPager viewPager;
	private IconButton btnSearch;
	private IconButton btnShopCar;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shop);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);

		headBar.setTitle("零售报单");
		headBar.setRightType(BtnType.empty);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();

		BtnTab tabLife = (BtnTab) findViewById(R.id.Btn_Life);
		BtnTab tabNurse = (BtnTab) findViewById(R.id.Btn_Nurse);
		BtnTab tabHome = (BtnTab) findViewById(R.id.Btn_Home);
		BtnTab tabCosmetic = (BtnTab) findViewById(R.id.Btn_Cosmetic);
		btnSearch = (IconButton) findViewById(R.id.Btn_Search);
		btnShopCar = (IconButton) findViewById(R.id.Btn_Shopp_Car);
		btnSearch.setOnClickListener(this);
		btnShopCar.setOnClickListener(this);
		btnShopCar.setCount(3);
		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		TabsAdapter tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), LifeFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), NurseFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), HealthFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), CosmeticFragment.class, null);

		tabController.addTab(tabLife);
		tabController.addTab(tabNurse);
		tabController.addTab(tabHome);
		tabController.addTab(tabCosmetic);
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
		case R.id.Btn_Search:

			break;

		case R.id.Btn_Shopp_Car:
			break;
		}

	}

}
