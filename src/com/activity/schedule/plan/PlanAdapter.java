package com.activity.schedule.plan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sansheng.R;
import com.sansheng.dao.interfaze.PlanDao;
import com.sansheng.model.Plan;

public class PlanAdapter extends BaseAdapter {

	private List<Plan> plans;
	private LayoutInflater layoutInflater;
	private Context c;
	private PlanDao planDao;
	private UiHandler uiHandler;
	private static final int MSG_UPDATE = 1;

	public PlanAdapter(Context context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		plans = new ArrayList<Plan>();
		uiHandler = new UiHandler();
		c = context;
	}

	@Override
	public int getCount() {
		if (plans == null) {
			return 0;
		}
		return plans.size();
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
		Plan plan = plans.get(position);
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_schedule_plan, null);

		}
		bindView(convertView, plan);
		return convertView;
	}

	public void bindView(View view, final Plan plan) {
		TextView tvContent = (TextView) view.findViewById(R.id.Tv_Content);
		tvContent.setText("您当月复消额为" + plan.getSum() + "pv");

		Button btnDelete = (Button) view.findViewById(R.id.Btn_Delete);
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					deletePlan(plan);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	private void deletePlan(final Plan plan) throws SQLException {
		planDao.delete(plan);
		plans.remove(plan);
		Message msg = new Message();
		msg.what = MSG_UPDATE;
		msg.obj = plans;
		uiHandler.sendMessage(msg);
	}

	public List<Plan> getLogistics() {
		return plans;
	}

	public void setLogistics(List<Plan> plans) {
		this.plans = plans;
		notifyDataSetChanged();
	}

	public PlanDao getLogisticsDao() {
		return planDao;
	}

	public void setLogisticsDao(PlanDao planDao) {
		this.planDao = planDao;
	}

	public class UiHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {

			case MSG_UPDATE:
				Toast.makeText(c, "正在更新", Toast.LENGTH_SHORT).show();
				notifyDataSetChanged();

				break;

			default:
				break;
			}

		}
	}

}
