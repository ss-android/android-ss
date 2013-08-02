package com.activity.schedule.birthday;

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

import com.example.sansheng.R;
import com.sansheng.model.Schedule;
import com.util.DateUtil;

public class BirthDayAdapter extends BaseAdapter {

	private ListView lvVisite;
	private List<Schedule> schedules;
	Activity activity;
	LayoutInflater layoutInflater;

	public BirthDayAdapter(Activity a) {
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
			View view = layoutInflater.inflate(
					R.layout.layout_schedule_birthday, null);
			TextView tvDay = (TextView) view.findViewById(R.id.Tv_Day);
			TextView tvCustome = (TextView) view.findViewById(R.id.Tv_Custome);

			if (schedule.getCustome_name() != null) {
				tvCustome.setText(schedule.getCustome_name() + "生日");
			}

			if (schedule.getData() != null) {
				Date dateSchedule = DateUtil.parse(schedule.getData());
				long dayDiff = DateUtil.getDateDiff(dateSchedule, new Date()) + 1;
				tvDay.setText(dayDiff + "天后");
			}
			convertView = view;
		}

		Button btnCall = (Button) convertView.findViewById(R.id.Btn_Call);

		Button btnSMS = (Button) convertView.findViewById(R.id.Btn_SMS);

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

		return convertView;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}
