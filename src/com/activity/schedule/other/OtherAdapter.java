package com.activity.schedule.other;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.util.DateUtil;
import com.view.OprationDilog;

public class OtherAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Schedule> schedules;
	Activity activity;
	LayoutInflater layoutInflater;
	private ScheduleDao scheduleDao;

	public OtherAdapter(Activity a) {
		activity = a;
		layoutInflater = (LayoutInflater) a
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		if (schedules == null) {
			return 0;
		} else {
			return schedules.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Schedule schedule = schedules.get(position);

		if (convertView == null) {
			View view = layoutInflater.inflate(R.layout.layout_schedule_other,
					null);

			convertView = view;
		}
		bindView(convertView, schedule);

		return convertView;
	}

	public void bindView(View view, final Schedule schedule) {

		TextView tvDay = (TextView) view.findViewById(R.id.Tv_Day);
		TextView tvCustome = (TextView) view.findViewById(R.id.Tv_Custome);
		TextView tvContent = (TextView) view.findViewById(R.id.Tv_Content);
		ImageButton btnDelete = (ImageButton) view
				.findViewById(R.id.Btn_Delete);
		if (schedule.getCustome_name() != null) {
			tvCustome.setText(schedule.getCustome_name());
		}

		if (schedule.getData() != null) {
			tvDay.setText(schedule.getData());
		}
		if (schedule.getContent() != null) {
			tvContent.setText(schedule.getContent());
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			@Override  
			public void onClick(View v) {
				Log.e("debug", "onclick");
				final OprationDilog dilog = new OprationDilog(activity);
				String content = activity.getResources().getString(
						R.string.sure_delete);

				dilog.setContent(content);
				dilog.onOkCallBack(new OnClickListener() {

					@Override
					public void onClick(View v) {
						delete(schedule);
						dilog.dismiss();
						schedules.remove(schedule);
						notifyDataSetChanged();
					}
				});
				dilog.show();

			}
		});

	}

	public void delete(Schedule schedule) {
		try {
			scheduleDao.delete(schedule);
			notifyDataSetChanged();
			Log.e("debug", "btnDelete  click");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

}
