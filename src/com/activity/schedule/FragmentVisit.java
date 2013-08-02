package com.activity.schedule;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.schedule.visite.VisiteAdapter;
import com.application.CommonApplication;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;

public class FragmentVisit extends Fragment implements Callback {

	private ListView lvVisite;
	private VisiteAdapter visiteAdapter;
	private View view;
	private CommonActivity commonActivity;
	private ScheduleDao scheduleDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_remind_visit, null);

		initWidget();
		return view;
	}

	public void initWidget() {
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		scheduleDao = comapp.getOrmDateBaseHelper().getScheduleDao();
		List<Schedule> schedules = scheduleDao.getScheduleByType(Type.visit);

		lvVisite = (ListView) view.findViewById(R.id.Lv_Visite);
		visiteAdapter = new VisiteAdapter(commonActivity);
		visiteAdapter.setSchedules(schedules);
		lvVisite.setAdapter(visiteAdapter);

		lvVisite.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Log.e("debug", "onItenmClick");
				Schedule schedule = visiteAdapter.getSchedules().get(position);
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

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
