package com.activity.shop;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts.Intents.UI;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.activity.CommonActivity;
import com.activity.index.IndexActivity;
import com.activity.schedule.TabsAdapter;
import com.activity.shop.car.ShopCarActivity;
import com.activity.shop.detail.ShopInfoFragment;
import com.activity.shop.life.LifeFragment;
import com.activity.shop.search.SearchActivity;
import com.http.BaseRequest;
import com.http.BrandAndAdverst;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.pr;
import com.util.ProgressDialogUtil;
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
	List<pr> prlist;
	BtnTab tabLife;
	BtnTab tabNurse;
	BtnTab tabHome;
	BtnTab tabCosmetic;
	TabsAdapter tabsAdapter;
	List<LifeFragment> lifeFragments;
	private CommonActivity activity;
	Dataloader[] dataloaders = new Dataloader[4];
	private UiHandler handler;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		handler = new UiHandler();
		setContentView(R.layout.activity_shop);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		for (int i = 0; i < 4; i++) {
			Dataloader d = new Dataloader();
			dataloaders[i] = d;
		}

		 
		lifeFragments = new ArrayList<LifeFragment>();
		headBar.setTitle("零售报单");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabController = new TabController();

		tabLife = (BtnTab) findViewById(R.id.Btn_Life);
		tabNurse = (BtnTab) findViewById(R.id.Btn_Nurse);
		tabHome = (BtnTab) findViewById(R.id.Btn_Home);
		tabCosmetic = (BtnTab) findViewById(R.id.Btn_Cosmetic);
		btnSearch = (IconButton) findViewById(R.id.Btn_Search);
		btnShopCar = (IconButton) findViewById(R.id.Btn_Shopp_Car);
		btnSearch.setOnClickListener(this);
		btnShopCar.setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab(), LifeFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), LifeFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), LifeFragment.class, null);
		tabsAdapter.addTab(actionBar.newTab(), LifeFragment.class, null);

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

				Log.e("debug", "item:" + item);
				Dataloader d = dataloaders[item];
				Log.e("dataload", "loading" + item);
				if (prlist != null) {
					BaseRequest baseRequest = createRequest(ShopService.SHOP_PRBC_SERIZAL);
					baseRequest.add("cid", prlist.get(item).getPrclass_id());
					new ShopAsyncTask(activity).execute(baseRequest);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.e("debug", "scroo");
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.e("debug", "scroo");

			}
		});
		BaseRequest requert = createRequest(ShopService.SHOP_PRBC_LIST);
		new ShopAsyncTask(this).execute(requert);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Search:
			Intent intentSearch = new Intent(this, SearchActivity.class);
			startActivity(intentSearch);
			break;
		case R.id.Btn_Shopp_Car:
			Intent intentShopCar = new Intent(this, ShopCarActivity.class);
			startActivity(intentShopCar);
			break;
		case R.id.Btn_Back:
			Intent i = new Intent(this, IndexActivity.class);
			startActivity(i);
			break;
		}

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case ShopService.SHOP_PRBC_LIST:

			// ProgressDialogUtil.show(this, "提示", "", true, true);
			prlist = (List<pr>) viewCommonResponse.getData();
			for (int i = 0; i < prlist.size(); i++) {
				BtnTab btnTab = tabController.getItem(i);
				pr pr = prlist.get(i);
				btnTab.setBtnTitle(pr.getPrclass_name());
			}
			BaseRequest baseRequest = createRequest(ShopService.SHOP_PRBC_SERIZAL);
			baseRequest.add("cid", prlist.get(0).getPrclass_id());
			new ShopAsyncTask(this).execute(baseRequest);

			break;

		case ShopService.SHOP_PRBC_SERIZAL:
			BrandAndAdverst ba = (BrandAndAdverst) viewCommonResponse.getData();

			int current = viewPager.getCurrentItem();
			LifeFragment lifeFragment = (LifeFragment) tabsAdapter
					.instantiateItem(viewPager, current);
			Dataloader d = dataloaders[current];
			d.setLoaded(true);
			Log.e("lifeFragemnt", lifeFragment.toString() + "");
			lifeFragment.update(ba);
			// ProgressDialogUtil.close();
			break;
		case ShopService.PRODUCT_ADD:
			ProgressDialogUtil.close();
			int newscartmun = (Integer) viewCommonResponse.getData();
			Toast.makeText(activity, "加入购物车完成", Toast.LENGTH_SHORT).show();
			Message msg = new Message();
			msg.what = 1;
			msg.obj = newscartmun;
			handler.sendMessage(msg);

			break;
		}
	}

	class UiHandler extends Handler {
		public void dispatchMessage(android.os.Message msg) {
			int id = msg.what;
			switch (id) {
			case 1:
				int newscartmun = msg.what;
				btnShopCar.setCount(newscartmun);
				break;

			default:
				break;
			}

		};
	}

	class Dataloader {
		private boolean isLoaded;

		public boolean isLoaded() {
			return isLoaded;
		}

		public void setLoaded(boolean isLoaded) {
			this.isLoaded = isLoaded;
		}

	}

}
