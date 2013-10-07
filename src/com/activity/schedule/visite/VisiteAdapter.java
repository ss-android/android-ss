package com.activity.schedule.visite;

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
import android.widget.ListView;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Schedule;
import com.util.DateUtil;
import com.view.OprationDilog;

public class VisiteAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Schedule> schedules;
	Activity activity;
	private ScheduleDao scheduleDao;
	LayoutInflater layoutInflater;

	public VisiteAdapter(Activity a) {
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
			View view = layoutInflater.inflate(R.layout.layout_schedule_visite,
					null);
			TextView tvDay = (TextView) view.findViewById(R.id.Tv_Day);
			TextView tvCustome = (TextView) view.findViewById(R.id.Tv_Custome);
			TextView tvContent = (TextView) view
					.findViewById(R.id.Tv_Visite_Conten);
			if (schedule.getCustome_name() != null) {
				tvCustome.setText(schedule.getCustome_name());
			}
			if (schedule.getContent() != null) {
				tvContent.setText(schedule.getContent());
			}

			if (schedule.getData() != null) {
				Date dateSchedule = DateUtil.parse(schedule.getData());
				long dayDiff = DateUtil.getDateDiff(dateSchedule, new Date()) + 1;
				tvDay.setText(dayDiff + "天后");
			}
			convertView = view;
		}

		Button btnCall = (Button) convertView.findViewById(R.id.Btn_Cal);

		Button btnSMS = (Button) convertView.findViewById(R.id.Btn_SM);

		Button btnDelete = (Button) convertView.findViewById(R.id.Btn_Delet);

		if (schedule.getPhoneNumber() == null
				|| schedule.getPhoneNumber().equals("")) {
			btnCall.setVisibility(View.INVISIBLE);
			btnSMS.setVisibility(View.INVISIBLE);
		}

		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("debug", "phone:" + schedule.getPhoneNumber());

				Uri uri = Uri.parse("tel:" + schedule.getPhoneNumber());
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				activity.startActivity(intent);
			}
		});
		btnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri smsToUri = Uri.parse("smsto://" + schedule.getPhoneNumber());
				Intent mIntent = new Intent(
						android.content.Intent.ACTION_SENDTO, smsToUri);
				activity.startActivity(mIntent);
			}
		});

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
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

		return convertView;
	}

	public void delete(Schedule schedule) {
		try {
			scheduleDao.delete(schedule);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schedules.remove(schedule);
		notifyDataSetChanged();
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
