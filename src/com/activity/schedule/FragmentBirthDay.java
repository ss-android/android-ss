package com.activity.schedule;

import java.util.List;

import com.activity.CommonActivity;
import com.activity.schedule.birthday.BirthDayAdapter;
import com.activity.schedule.visite.VisiteAdapter;
import com.application.CommonApplication;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentBirthDay extends Fragment {

	private ListView lvVisite;
	private BirthDayAdapter brithDayAdapter;
	private View view;
	private CommonActivity commonActivity;
	private ScheduleDao scheduleDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_remind_birthday, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		scheduleDao = comapp.getOrmDateBaseHelper().getScheduleDao();
		List<Schedule> schedules = scheduleDao.getScheduleByType(Type.birthday);
		lvVisite = (ListView) view.findViewById(R.id.Lv_Visite);
		brithDayAdapter = new BirthDayAdapter(commonActivity);
		brithDayAdapter.setSchedules(schedules);
		lvVisite.setAdapter(brithDayAdapter);

		lvVisite.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Log.e("debug", "onItenmClick");
				Schedule schedule = brithDayAdapter.getSchedules().get(position);
				Intent intent = new Intent(commonActivity,
						AddScheduleActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("schedule", schedule);
				intent.putExtras(bundle);
				startActivity(intent);
				commonActivity.finish();
			}
		});

	}
}
