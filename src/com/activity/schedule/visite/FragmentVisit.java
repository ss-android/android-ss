package com.activity.schedule.visite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.application.CommonApplication;
import com.http.RemindService;
import com.http.ViewCommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.model.Remind;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;
import com.util.ProgressDialogUtil;

public class FragmentVisit extends Fragment {
	private ListView lvVisite;
	private VisiteAdapter visiteAdapter;
	private View view;
	private CommonActivity commonActivity;
	private ScheduleDao scheduleDao;
	RemindService remindService;

	private static final int UPDATE = 0;
	private static final int DELETE = 1;
	private FragmentVisit fVisit;

	private UiHandler uiHandler;
	static List<Remind> reminds;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_remind_visit, null);

		initWidget();
		return view;
	}

	public void initWidget() {
		uiHandler = new UiHandler();
		remindService = new RemindService();
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		scheduleDao = comapp.getOrmDateBaseHelper().getScheduleDao();
		List<Schedule> schedules = scheduleDao.getScheduleByType(Type.visit);

		lvVisite = (ListView) view.findViewById(R.id.Lv_Visite);
		visiteAdapter = new VisiteAdapter(commonActivity);
		visiteAdapter.fragmentVisit = this;
		if (reminds != null) {
			visiteAdapter.setSchedules(reminds);
		}
		// visiteAdapter.setScheduleDao(scheduleDao);
		lvVisite.setAdapter(visiteAdapter);
		update();
	}

	public void update() {
		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
				p.put("type", "0");
				p.put("pageno", "0");
				ViewCommonResponse resp = remindService.queryRemind(p);
				reminds = (List<Remind>) resp.getData();
				Message msg = new Message();
				msg.obj = reminds;
				msg.what = 0;
				uiHandler.sendMessage(msg);
			};
		}.start();
	}

	public void delete() {
		ProgressDialogUtil.show(this.getActivity(), "删除中", "", true, true);

		new Thread() {
			public void run() {
				Map<String, String> p = new HashMap<String, String>();
				p.put("msid", visiteAdapter.curRemind.getRemindid());
				p.put("userid", "H7Mud3IiaWjWqdL4J4qEJA==");
				ViewCommonResponse resp = remindService.deleteRemind(p);

				if (resp.getHttpCode() == 200) {
					reminds = (List<Remind>) resp.getData();
					Message msg = new Message();
					msg.what = 1;
					uiHandler.sendMessage(msg);
				}
			};
		}.start();
	}

	class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case 0:
				List<Remind> reminds = (List<Remind>) msg.obj;
				visiteAdapter.setSchedules(reminds);
				visiteAdapter.notifyDataSetChanged();
				break;

			case 1:
				ProgressDialogUtil.close();
				Remind remind = visiteAdapter.curRemind;
				visiteAdapter.getSchedules().remove(remind);
				visiteAdapter.notifyDataSetChanged();
				break;
			} 
		}
	}

}
