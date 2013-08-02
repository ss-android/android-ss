package com.activity.schedule.Logistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.LogisticsDao;
import com.sansheng.model.Logistics;


//修改
public class FragmengLosistics extends Fragment {

	private View view;
	public static final int MSG_DATE = 1;
	private ListView lvLogistics;
	LogisticsAdapter logisticsAdapter;
	private UiHandler uiHandler;
	private CommonActivity commonActivity;
	private LogisticsDao logisticsDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = (View) inflater
				.inflate(R.layout.fragment_remind_logistics, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		commonActivity = (CommonActivity) getActivity();
		CommonApplication comapp = (CommonApplication) commonActivity
				.getApplication();
		logisticsDao = comapp.getOrmDateBaseHelper().getLogisticsDao();

		lvLogistics = (ListView) view.findViewById(R.id.Lv_Logistics);
		logisticsAdapter = new LogisticsAdapter(this.getActivity());
		lvLogistics.setAdapter(logisticsAdapter);
		logisticsAdapter.setLogisticsDao(logisticsDao);
		uiHandler = new UiHandler();

		new Thread() {
			public void run() {

				List<Logistics> logistics = null;
				try {
					logistics = logisticsDao.queryForAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Message msg = new Message();
				msg.what = MSG_DATE;
				msg.obj = logistics;
				uiHandler.sendMessage(msg);

			};
		}.start();

	}

	public class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_DATE:
				List<Logistics> logistics = (List<Logistics>) msg.obj;
				logisticsAdapter.setLogistics(logistics);
				break;

			default:
				break;
			}

		}

	}
}
