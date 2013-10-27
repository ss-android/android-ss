package com.activity.balance;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.schedule.CommonFragment;
import com.http.AchivementService;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ViewCommonResponse;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.util.ProgressDialogUtil;

public class PaymentFragment extends CommonFragment implements OnClickListener {
	protected View view;
	protected LayoutInflater layoutInflater;
	protected List<CustomForm> balance;
	private CommonActivity activity;
	private PaymentAdapter balanceAdapter;
	ListView lvBalance;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.activity = (CommonActivity) getActivity();
		view = (View) inflater.inflate(R.layout.activity_payment_list, null);
		layoutInflater = inflater;
		lvBalance = (ListView) view.findViewById(R.id.Lv_Balance_List);

		balance = new ArrayList<CustomForm>();
		balanceAdapter = new PaymentAdapter(activity, this);
		// CommonActivity comActivity = (CommonActivity) getActivity();
		// BalanceAdapter brandAdapter = new BalanceAdapter(comActivity);
		// brandAdapter.setBalance(balance);
		// lvBalancetype0.setAdapter(brandAdapter);
		initData();
		lvBalance.setAdapter(balanceAdapter);
		lvBalance.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("debug", "click");
			}
		});
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
			balance = (List<CustomForm>) viewCommonResponse.getData();
			if (balance != null) {
				balanceAdapter.setBalance(balance);
				balanceAdapter.notifyDataSetChanged();

			} else {
			}
			break;

		case CustomFormService.FORM_QUERY_ADD:
			balance = (List<CustomForm>) viewCommonResponse.getData();
			if (balance != null) {
				balanceAdapter.setBalance(balance);
				balanceAdapter.notifyDataSetChanged();

			} else {

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
}
