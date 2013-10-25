package com.activity.balance;

import java.util.ArrayList;
import java.util.List;

import com.activity.CommonActivity;
import com.http.ShopService;
import com.lekoko.sansheng.R;
import com.sansheng.model.Balance;
import com.sansheng.model.Product;
import com.view.ShopEditDialog;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BalanceAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private CommonActivity activity;
	private Uihandler uihandler;
	private List<Balance> balance;

	public BalanceAdapter(CommonActivity context) {
		this.activity = context;
		uihandler = new Uihandler();
		layoutInflater = (LayoutInflater) context.getLayoutInflater();
		balance = new ArrayList<Balance>();
	}

	public List<Balance> getBalance() {
		return balance;
	}

	public void setBalance(List<Balance> balance) {
		this.balance = balance;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (balance == null) {
			return 0;
		} else {
			return 5;
//					balance.size();
		}
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
		final Balance bl = balance.get(position);
		TextView tvBalanceno = (TextView) v.findViewById(R.id.Tv_Balanceno);
		TextView tvUserno = (TextView) v.findViewById(R.id.Tv_Userno);
		TextView tvUserName = (TextView) v.findViewById(R.id.Tv_UserName);
		TextView tvShopno = (TextView) v.findViewById(R.id.Tv_Shopno);
		TextView tvAllmoney = (TextView) v.findViewById(R.id.Tv_Allmoney);
		TextView tvAllPv = (TextView) v.findViewById(R.id.Tv_AllPv);
		ImageButton imgBtn_Del = (ImageButton) v.findViewById(R.id.imgBtn_Del);
		Button btn_Payment = (Button) v.findViewById(R.id.imgBtn_Payment);

		if (bl.getBalanceno() != null) {
			tvBalanceno.setText(bl.getBalanceno());
		}
		if (bl.getUserno() != null) {
			tvUserno.setText(bl.getUserno());
		}
		tvUserName.setText(bl.getUsername());
		tvShopno.setText(bl.getProduc());

		Balance blist = balance.get(position);

	}

	class Uihandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {

		}
	}
}
