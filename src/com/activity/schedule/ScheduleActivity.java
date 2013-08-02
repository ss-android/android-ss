package com.activity.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.activity.CommonActivity;
import com.activity.index.IndexActivity;
import com.activity.schedule.Logistics.FragmengLosistics;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.view.BtnTab;
import com.view.HeadBar;

public class ScheduleActivity extends CommonActivity implements OnClickListener {
	private ViewPager viewPager;
	private TabsAdapter tabsAdapter;
	private ActionBar actionBar;
	private ScheduleDao scheduleDao;
	private Schedule schedule;

	private Button btnAdd;
	private ImageButton btnBack;

	private BtnTab tabVisit;
	private BtnTab tabBirthDay;
	private BtnTab tabOther;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		initWidget();
		ckeckIntent(getIntent());
	}

	public void ckeckIntent(Intent intent) {
		if (intent == null || intent.getExtras() == null) {
			return;
		}
		int tabIndex = intent.getExtras().getInt("tabIndex");
		viewPager.setCurrentItem(tabIndex);
		setCurrentTab(tabIndex);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// return super.onCreateOptionsMenu(menu);
		// MenuItem add = menu.add(0, 1, 0, "添加");
		// add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.e("debug", item.getItemId() + "");
		Intent intent = new Intent(this, AddScheduleActivity.class);
		startActivity(intent);
		finish();
		return super.onOptionsItemSelected(item);
	}

	public void setCurrentTab(int index) {
		tabBirthDay.unSleetced();
		tabOther.unSleetced();
		tabVisit.unSleetced();
		if (index == 0) {
			tabVisit.selected();
		}
		if (index == 1) {
			tabBirthDay.selected();
		}
		if (index == 2) {
			tabOther.selected();
		}
	}

	public void initWidget() {
		actionBar = getSupportActionBar();
		// actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setTitle(R.string.schedule_alert);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.hide();

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setWidgetClickListener(this);
		headBar.setBtnRightText("添加");
		btnAdd = (Button) findViewById(R.id.Btn_Right);
		btnBack = (ImageButton) findViewById(R.id.Btn_Back);
		btnAdd.setOnClickListener(this);
		btnBack.setOnClickListener(this);

		tabVisit = (BtnTab) findViewById(R.id.Btn_Visit);
		tabVisit.selected();
		tabBirthDay = (BtnTab) findViewById(R.id.Btn_Party);
		tabOther = (BtnTab) findViewById(R.id.Btn_Other);

		tabVisit.setOnClickListener(this);
		tabBirthDay.setOnClickListener(this);
		tabOther.setOnClickListener(this);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab().setText("拜访提醒"),
				FragmentVisit.class, null);
		tabsAdapter.addTab(actionBar.newTab().setText("生日提醒"),
				FragmentBirthDay.class, null);
		tabsAdapter.addTab(actionBar.newTab().setText("物流提醒"),
				FragmengLosistics.class, null);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int index) {
				setCurrentTab(index);
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
		case R.id.Btn_Right:
			Intent intent = new Intent(this, AddScheduleActivity.class);
			startActivity(intent);

			break;

		case R.id.Btn_Back:
			back();
			break;
		case R.id.Btn_Visit:
			viewPager.setCurrentItem(0);
			break;
		case R.id.Btn_Party:
			viewPager.setCurrentItem(1);
			break;
		case R.id.Btn_Other:
			viewPager.setCurrentItem(2);
			break;

		}

	}

	private void back() {
		Intent i = new Intent(this, IndexActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		back();

	}

}
