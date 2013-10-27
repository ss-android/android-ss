package com.activity.balance;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ViewCommonResponse;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.util.ProgressDialogUtil;

public class UnpaymentFragment extends CommonFragment implements
		OnClickListener, OnScrollListener {
	protected View view;
	protected LayoutInflater layoutInflater;
	protected List<CustomForm> balance;
	private CommonActivity activity;
	private unPaymentAdapter balanceAdapter;
	ListView lvBalance;
	private boolean canUpdate = false;

	protected long currentPage = 0;
	protected int pageRecords = 10;
	protected int totalPages = 1;
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.activity_balance_list, null);
		layoutInflater = inflater;
		lvBalance = (ListView) view.findViewById(R.id.Lv_Balance_List);

		balance = new ArrayList<CustomForm>();
		balanceAdapter = new unPaymentAdapter(activity, this);
		// CommonActivity comActivity = (CommonActivity) getActivity();
		// BalanceAdapter brandAdapter = new BalanceAdapter(comActivity);
		// brandAdapter.setBalance(balance);
		// lvBalancetype0.setAdapter(brandAdapter);
		initData();
		lvBalance.setOnScrollListener(this);
		lvBalance.setAdapter(balanceAdapter);
 
		return view;
	}

	/**
	 * 初始化数据
	 * 
	 * @param achList
	 */
	public void initData() {

		BaseRequest requert = activity
				.createRequestWithUserId(CustomFormService.FORM_QUERY);// action名称

		requert.add("querytype", "0");
		requert.add("pageno", "0");
		requert.add("shopshowid", "all");
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
			balance = (List<CustomForm>) viewCommonResponse.getData();
			if (balance != null) {
				balanceAdapter.setBalance(balance);
				balanceAdapter.notifyDataSetChanged();
				canUpdate = true;

			} else {
			}
			break;

		case CustomFormService.FORM_QUERY_ADD:
			balance = (List<CustomForm>) viewCommonResponse.getData();

			if (balance != null) {
				balanceAdapter.getBalance().addAll(balance);
				balanceAdapter.notifyDataSetChanged();
				if (balance.size() < pageRecords) {
					canUpdate = false;
				}
			} else {
				canUpdate = false;
			}
			break;

		case CustomFormService.FORM_DELETE:
			ProgressDialogUtil.close();
			if (viewCommonResponse.getMsgCode() == 0) {
				balanceAdapter.remove();
				balanceAdapter.notifyDataSetChanged();
			} else {
				activity.showToast("删除失败");
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void onScroll(AbsListView view, int firstVisibleItem,
	// int visibleItemCount, int totalItemCount) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

		if (canUpdate && arg3 != 0 && arg1 + arg2 == arg3) {
			canUpdate = false;
			BaseRequest requert = activity
					.createRequestWithUserId(CustomFormService.FORM_QUERY_ADD);// action名称
			currentPage++;
			requert.add("querytype", "0");
			requert.add("pageno", currentPage + "");
			requert.add("shopshowid", "all");
			new FormAsyncTask(null, this).execute(requert);
			currentPage++;
		}

		if (currentPage - 1 >= totalPages && totalPages != -1) {
		}
	}
}