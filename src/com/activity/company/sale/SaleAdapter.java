package com.activity.company.sale;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

public class SaleAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

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
