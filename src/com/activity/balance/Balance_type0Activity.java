package com.activity.balance;

import java.util.ArrayList;
import java.util.List;

import com.activity.CommonActivity;
import com.activity.achievement.AchievementActivity;
import com.activity.schedule.CommonFragment;
import com.google.gson.Gson;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ViewCommonResponse;
import com.http.task.AchivementAsyncTask;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.AchList;
import com.sansheng.model.Balance;
import com.sansheng.model.SalePool;
import com.util.ProgressDialogUtil;
import com.view.OnWheelChangedListener;
import com.view.WheelAdapter;
import com.view.WheelView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class Balance_type0Activity extends CommonFragment implements
		OnClickListener {
	protected View view;
	protected LayoutInflater layoutInflater;
	protected List<Balance> balance;
	private CommonActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.activity_balance_list, null);
		layoutInflater = inflater;
		ListView lvBalancetype0 = (ListView) view
				.findViewById(R.id.Lv_Balance_List);

		balance = new ArrayList<Balance>();

//		CommonActivity comActivity = (CommonActivity) getActivity();
//		BalanceAdapter brandAdapter = new BalanceAdapter(comActivity);
//		brandAdapter.setBalance(balance);
//		lvBalancetype0.setAdapter(brandAdapter);
		return view;
	}

	/**
	 * 初始化数据
	 * 
	 * @param achList
	 */
	public void initData(List<Balance> blaList) {

		BaseRequest requert = activity
				.createRequestWithUserId(CustomFormService.FORM_QUERY);// action名称
		
		Gson gson = new Gson();
		String json = gson.toJson(blaList);
		requert.add("querytype", "0");
		requert.add("pageno", "1");
		
		new FormAsyncTask(null, this).execute(requert);
		ProgressDialogUtil.show(activity, "提示", "正在加载数据", true, true);
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		switch (action) {
		case CustomFormService.FORM_QUERY:
			ProgressDialogUtil.close();
			balance = (List<Balance>) viewCommonResponse.getData();
			BalanceActivity.bList = balance;
			if (balance != null && balance.size() >= 1) {
				initData(balance);
			} else {
				ProgressDialogUtil.close();
			}
			break;

		case AchivementService.ACHI_SALE:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
