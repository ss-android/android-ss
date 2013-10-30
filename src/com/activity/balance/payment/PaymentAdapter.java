package com.activity.balance.payment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.balance.UnPaymentDetailActivity;
import com.activity.schedule.CommonFragment;
import com.activity.shop.payment.PaymentActivity;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.task.FormAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.sansheng.model.User;
import com.util.ProgressDialogUtil;

public class PaymentAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private CommonActivity activity;
	private Uihandler uihandler;
	private List<CustomForm> balance;
	public int currentP;
	CommonFragment fragment;
	public boolean isShoper;

	public PaymentAdapter(CommonActivity context, CommonFragment cf,
			boolean shoper) {
		this.activity = context;
		fragment = cf;
		uihandler = new Uihandler();
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
		balance = new ArrayList<CustomForm>();
		isShoper = shoper;
	}

	public List<CustomForm> getBalance() {
		return balance;
	}

	public void setBalance(List<CustomForm> balance) {
		this.balance = balance;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (balance == null) {
			return 0;
		}
		return balance.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_balance_type0, null);
		}
		bindView(convertView, arg0);
		return convertView;
	}

	public void bindView(View v, final int position) {
		final CustomForm bl = balance.get(position);
		View layoutView = v.findViewById(R.id.Layout);
		layoutView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-gene‰rated method stub
				Log.e("debug", "click");
				Log.e("debug", "click");
				User user = activity.getUser();
				if (user.getLogintype() == 0) {
					Intent intent = new Intent(activity,
							UnPaymentDetailActivity.class);
					intent.setAction(UnPaymentDetailActivity.ACTION_MEMBER_PAY);
					Bundle b = new Bundle();
					b.putSerializable("from", bl); 
					intent.putExtras(b);
					activity.startActivity(intent);
				} else {
					Intent intent = new Intent(activity,
							UnPaymentDetailActivity.class);
					intent.setAction(UnPaymentDetailActivity.ACTION_SHOP_PAY);
					Bundle b = new Bundle();
					b.putSerializable("from", bl);
					intent.putExtras(b);
					activity.startActivity(intent);
				}
			}
		});

		TextView tvBalanceno = (TextView) v.findViewById(R.id.Tv_Balanceno);
		TextView tvUserno = (TextView) v.findViewById(R.id.Tv_Userno);
		TextView tvUserName = (TextView) v.findViewById(R.id.Tv_UserName);
		TextView tvShopno = (TextView) v.findViewById(R.id.Tv_Shopno);
		TextView tvAllmoney = (TextView) v.findViewById(R.id.Tv_Allmoney);
		TextView tvAllPv = (TextView) v.findViewById(R.id.Tv_AllPv);
		TextView state = (TextView) v.findViewById(R.id.Tv_State);

		if (bl.getIsfahuo() != null) {
			state.setText(bl.getIsfahuo());
		}
		if (bl.getBalanceno() != null) {
			tvBalanceno.setText(bl.getBalanceno());
		}
		if (bl.getBalanceno() != null) {
			tvUserno.setText(bl.getBalanceid());
		}
		if (bl.getUsername() != null) {
			tvUserName.setText(bl.getUsername());
		}
		if (bl.getShopno() != null) {
			tvShopno.setText(bl.getShopno());
		}
		if (bl.getAllmoney() != null) {
			tvAllmoney.setText(bl.getAllmoney());
		}
		if (bl.getAllpv() != null) {
			tvAllPv.setText(bl.getAllpv());
		}

		Button btnNotify = (Button) v.findViewById(R.id.btn_notify);
		Button btnPick = (Button) v.findViewById(R.id.btn_pcik);

		if (isShoper == true) {
			btnNotify.setVisibility(View.VISIBLE);
			btnPick.setVisibility(View.VISIBLE);
			btnNotify.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					currentP = position;
					AlertDialog.Builder builder = new Builder(activity);
					builder.setMessage("发送提醒？");
					builder.setTitle("提示");
					currentP = position;
					builder.setPositiveButton("确认",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									BaseRequest requert = activity
											.createRequestWithUserId(CustomFormService.FORM_NOTIFY);// action名称
									requert.add("balanceid", bl.getBalanceid());
									requert.add("balanceno", bl.getBalanceno());
									new FormAsyncTask(null, fragment)
											.execute(requert);

								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

								}
							});
					builder.show();

				}
			});
			btnPick.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					currentP = position;
					AlertDialog.Builder builder = new Builder(activity);
					builder.setMessage("确认收获？");
					builder.setTitle("提示");
					currentP = position;
					builder.setPositiveButton("确认",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									BaseRequest requert = activity
											.createRequestWithUserId(CustomFormService.FORM_Sure);// action名称
									requert.add("balanceid", bl.getBalanceid());
									requert.add("balanceno", bl.getBalanceno());
									new FormAsyncTask(null, fragment)
											.execute(requert);

								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

								}
							});
					builder.show();

				}
			});
		} else {
			btnNotify.setVisibility(View.GONE);
			btnPick.setVisibility(View.GONE);
		}

	}

	class Uihandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {

		}
	}

	public void remove() {
		CustomForm cf = balance.get(currentP);
		balance.remove(cf);

	}
}
