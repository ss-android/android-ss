package com.activity.bill;

import java.util.List;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.ViewCommonResponse;
import com.http.task.AchivementAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.AchList;
import com.sansheng.model.Achivement;
import com.util.ProgressDialogUtil;
import com.view.OnWheelChangedListener;
import com.view.WheelAdapter;
import com.view.WheelView;

public class MyAchievement extends CommonFragment implements OnClickListener {

	protected View view;
	protected LayoutInflater layoutInflater;
	private static Achivement achivement;
	static TextView Tv_Group_Num;

	static TextView Tv_Current_Num;
	static TextView Tv_Retail_Num;
	static TextView Tv_Proportion_Num;
	static TextPaint tp_Retail_Num;
	private CommonActivity activity;
	List<AchList> achLists;
	RatingBar rateMax;
	RatingBar rateOk;

	private WheelView wheelView;
	private WheelAdapter wheelAdapter;

	private Button btnCancel;
	private Button btnOk;
	private int currentItem;
	private RelativeLayout pickerLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.layout_results_myresults, null);
		layoutInflater = inflater;

		Button resultsview = (Button) view.findViewById(R.id.btn_periods);
		resultsview.setOnClickListener(this);
		// resultsview.setWidth(320);
		pickerLayout = (RelativeLayout) view.findViewById(R.id.Layout_Picker);
		rateMax = (RatingBar) view.findViewById(R.id.ratingBar1);
		rateOk = (RatingBar) view.findViewById(R.id.Rb_ThisMonth);
		// 字体加粗
		Tv_Group_Num = (TextView) view.findViewById(R.id.Tv_Group_Num);
		TextPaint tp_Group_Num = Tv_Group_Num.getPaint();
		tp_Group_Num.setFakeBoldText(true);

		Tv_Current_Num = (TextView) view.findViewById(R.id.Tv_Current_Num);
		TextPaint tp_Current_Num = Tv_Current_Num.getPaint();
		tp_Current_Num.setFakeBoldText(true);

		Tv_Retail_Num = (TextView) view.findViewById(R.id.Tv_Retail_Num);
		tp_Retail_Num = Tv_Retail_Num.getPaint();
		wheelView = (WheelView) view.findViewById(R.id.WheelView);
		tp_Retail_Num.setFakeBoldText(true);

		Tv_Proportion_Num = (TextView) view
				.findViewById(R.id.Tv_Proportion_Num);
		TextPaint tp_Proportion_Num = Tv_Proportion_Num.getPaint();
		tp_Proportion_Num.setFakeBoldText(true);

		btnCancel = (Button) view.findViewById(R.id.Btn_Cancel);
		btnOk = (Button) view.findViewById(R.id.Btn_Sure);

		btnCancel.setOnClickListener(this);
		btnOk.setOnClickListener(this);
		initDataList();
		return view;
	}

	public void initDataList() {
		if (achLists == null) {
			BaseRequest requert = activity
					.createRequestWithUserId(AchivementService.ACHI_LIST);
			new AchivementAsyncTask(null, this).execute(requert);
			ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);
		}
	}

	public void initData(AchList achList) {

		BaseRequest requert = activity
				.createRequestWithUserId(AchivementService.ACHI_MY);
		requert.add("qsid", achList.getPeriodsid());
		new AchivementAsyncTask(null, this).execute(requert);
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);

	}

	public void updata(Achivement a) {
		achivement = a;
		String s = a.getMaxmanagerlevel();

		int start = 0;
		int start2 = 0;
		if (s.equals("1星")) {
			start = 1;
		}
		if (s.equals("2星")) {
			start = 2;
		}
		if (s.equals("3星")) {
			start = 3;
		}
		if (s.equals("4星")) {
			start = 4;
		}
		if (s.equals("5星")) {
			start = 5;
		}
		s = a.getStandardmanagerlevel();
		if (s.equals("1星")) {
			start2 = 1;
		}
		if (s.equals("2星")) {
			start2 = 2;
		}
		if (s.equals("3星")) {
			start2 = 3;
		}
		if (s.equals("4星")) {
			start2 = 4;
		}
		if (s.equals("5星")) {
			start2 = 5;
		}
		rateMax.setRating(start);
		rateOk.setRating(start2);
		Tv_Group_Num.setText(achivement.getGroupevaluation());

		Tv_Current_Num.setText(achivement.getAllexpense());
		Tv_Retail_Num.setText(achivement.getAllresale());
		Tv_Proportion_Num.setText(achivement.getResalepercent() + "%");
	}

	// public void initWidget() {
	// lvEvaluate = (ListView) view.findViewById(R.id.Lv_Evaluate);
	// lvEvaluate.setDivider(null);
	// EvaluateAdapter adapter = new EvaluateAdapter(commonActivity, getData());
	// lvEvaluate.setAdapter(adapter);
	// }

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case AchivementService.ACHI_LIST:
			ProgressDialogUtil.close();
			achLists = (List<AchList>) viewCommonResponse.getData();
			AchievementActivity.achLists = achLists;
			if (achLists != null && achLists.size() >= 1) {
				initData(achLists.get(0));
			} else {
				ProgressDialogUtil.close();
			}
			wheelAdapter = new WheelAdapter() {

				@Override
				public int getMaximumLength() {
					// TODO Auto-generated method stub
					return achLists.size();
				}

				@Override
				public int getItemsCount() {
					return achLists.size();
				}

				@Override
				public String getItem(int index) {
					// TODO Auto-generated method stub
					AchList achList = achLists.get(index);
					String content = "";
					if (achList.getTitle() != null) {
						content += achList.getTitle();
					}
					if (achList.getTimes() != null) {
						content += achList.getTimes();
					}

					return content;
				}
			};
			wheelView.setAdapter(wheelAdapter);
			wheelView.addChangingListener(new OnWheelChangedListener() {

				@Override
				public void onChanged(WheelView wheel, int oldValue,
						int newValue) {
					// TODO Auto-generated method stub
					Log.e("de bug",
							"value" + newValue + " item:"
									+ wheel.getCurrentItem());
					currentItem = wheel.getCurrentItem();
				}
			});
			break;

		case AchivementService.ACHI_MY:
			AchList achList = achLists.get(0);
			ProgressDialogUtil.close();
			Achivement achivement = (Achivement) viewCommonResponse.getData();
			updata(achivement);
			break;
		}
	}

	 

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Cancel:
			pickerLayout.setVisibility(View.GONE);
			break;

		case R.id.Btn_Sure:
			initData(achLists.get(currentItem));
			pickerLayout.setVisibility(View.GONE);
			break;
		case R.id.btn_periods:
			pickerLayout.setVisibility(View.VISIBLE);
			break;
		}
	}

}
