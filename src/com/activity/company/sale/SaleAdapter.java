package com.activity.company.sale;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.activity.company.InfoDetailActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

public class SaleAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;
	public Activity activity;
	private LayoutInflater layoutInflater;

	public SaleAdapter(Context context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (localInfos == null) {
			return 0;
		} else {
			return localInfos.size();
		}
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private class ViewHolder {
		public TextView tvTitle;
		public TextView tvData;
		public Button btnDetail;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int p = position;
		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_sale, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Title);
			vHolder.btnDetail = (Button) convertView
					.findViewById(R.id.Btn_Detail);
			convertView.setTag(vHolder);
		}

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LocalInfo localInfo = localInfos.get(p);
				Log.e("debug", "url");
				Intent i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "促销详情");
				i.putExtra(InfoDetailActivity.URL, localInfo.getUrl());
				activity.startActivity(i);

			}
		});

		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);
		return convertView;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		if (localInfo.getTitle() != null) {
			viewHolder.tvTitle.setText(localInfo.getTitle());
		}
		viewHolder.btnDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("debug", "onclick");
			}
		});

	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

}
