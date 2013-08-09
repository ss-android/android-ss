package com.activity.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.activity.CommonActivity;
import com.activity.index.IndexActivity;
import com.activity.schedule.Logistics.FragmengLosistics;
import com.activity.schedule.birthday.FragmentBirthDay;
import com.activity.schedule.other.FragmentOther;
import com.activity.schedule.plan.FragmentPlan;
import com.activity.schedule.visite.FragmentVisit;
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

	private BtnTab tabPlan;

	private FragmentOther fragmentOther;

	private Button btnAlertSchedule;
	private Button btnScheduleArrange;
	private LinearLayout layout_Tab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Sherlock_Light);
		setContentView(R.layout.activity_schedule);
		initWidget();
		ckeckIntent(getIntent());
	}

	public void ckeckIntent(Intent intent) {
		if (intent == null || intent.getExtras() == null) {
			return;
		}
		int tabIndex = intent.getExtras().getInt("tabIndex");
		if (tabIndex != 5) {
			viewPager.setCurrentItem(tabIndex);
			setCurrentTab(tabIndex);
		} else {
			selectBottomTab(2);
		}
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
		tabPlan.unSleetced();
		if (index == 0) {
			tabVisit.selected();
		}
		if (index == 1) {
			tabBirthDay.selected();
		}
		if (index == 2) {
			tabOther.selected();
		}
		if (index == 3) {
			tabPlan.selected();
		}    
	}
    
	public void initWidget() {
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setWidgetClickListener(this);
		headBar.setBtnRightText("添加");
		btnAdd = (Button) findViewById(R.id.Btn_Right);
		btnBack = (ImageButton) findViewById(R.id.Btn_Back);
		btnAlertSchedule = (Button) findViewById(R.id.Btn_Alert_Item);
		btnScheduleArrange = (Button) findViewById(R.id.Btn_Schedule_Arrange);
		layout_Tab = (LinearLayout) findViewById(R.id.RL_Tab);
		btnScheduleArrange.setOnClickListener(this);
		btnAlertSchedule.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		btnBack.setOnClickListener(this);

		tabVisit = (BtnTab) findViewById(R.id.Btn_Visit);
		tabVisit.selected();
		tabBirthDay = (BtnTab) findViewById(R.id.Btn_Party);
		tabOther = (BtnTab) findViewById(R.id.Btn_Logistics);
		tabPlan = (BtnTab) findViewById(R.id.Btn_Plan);
		tabVisit.setOnClickListener(this);
		tabBirthDay.setOnClickListener(this);
		tabOther.setOnClickListener(this);
		tabPlan.setOnClickListener(this);

		viewPager = (ViewPager) findViewById(R.id.ViewPaper_Content);
		tabsAdapter = new TabsAdapter(this, viewPager);
		tabsAdapter.addTab(actionBar.newTab().setText("拜访提醒"),
				FragmentVisit.class, null);
		tabsAdapter.addTab(actionBar.newTab().setText("生日提醒"),
				FragmentBirthDay.class, null);
		tabsAdapter.addTab(actionBar.newTab().setText("物流提醒"),
				FragmengLosistics.class, null);

		tabsAdapter.addTab(actionBar.newTab().setText("复消提醒"),
				FragmentPlan.class, null);
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

		fragmentOther = (FragmentOther) getSupportFragmentManager()
				.findFragmentById(R.id.Fragemnt_Other);
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
		case R.id.Btn_Logistics:
			viewPager.setCurrentItem(2);
			break;
		case R.id.Btn_Plan:
			viewPager.setCurrentItem(3);
			break;

		case R.id.Btn_Alert_Item:
			selectBottomTab(1);
			break;

		case R.id.Btn_Schedule_Arrange:
			selectBottomTab(2);
			break;

		}

	}

	public void selectBottomTab(int index) {
		if (index == 1) {
			viewPager.setVisibility(View.VISIBLE);
			layout_Tab.setVisibility(View.VISIBLE);
			btnAlertSchedule.setBackgroundColor(Color.parseColor("#ffffff"));
			btnScheduleArrange.setBackgroundColor(Color.parseColor("#cccccc"));
		} else {
			viewPager.setVisibility(View.INVISIBLE);
			layout_Tab.setVisibility(View.INVISIBLE);
			btnAlertSchedule.setBackgroundColor(Color.parseColor("#cccccc"));
			btnScheduleArrange.setBackgroundColor(Color.parseColor("#ffffff"));

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
