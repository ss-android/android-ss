package com.activity.company.produce;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

public class ProduceAdapter extends BaseAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	public ProduceAdapter(Context context) {
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

	public class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public ImageView img;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LocalInfo localInfo = localInfos.get(position);

		if (convertView == null) {
			ViewHolder vHolder = null;
			if (position == 0 || (position == localInfos.size() - 1)) {
				convertView = (View) layoutInflater.inflate(
						R.layout.layout_company_produce_headview, null);
				vHolder = new ViewHolder();
				vHolder.tvTitle = (TextView) convertView
						.findViewById(R.id.Tv_HeadTitle);
				vHolder.tvContent = (TextView) convertView
						.findViewById(R.id.Tv_HeadContent);
			} else {
				convertView = (View) layoutInflater.inflate(
						R.layout.layout_company_produce, null);
				vHolder = new ViewHolder();
				// vHolder.tvTitle = (TextView) convertView
				// .findViewById(R.id.Tv_Cultural_Title);
				vHolder.tvTitle = (TextView) convertView
						.findViewById(R.id.Tv_CenterTitle);
				vHolder.tvContent = (TextView) convertView
						.findViewById(R.id.Tv_CenterContent);
				vHolder.img = (ImageView) convertView
						.findViewById(R.id.Iv_CenterImg);
			}
			convertView.setTag(vHolder);
			ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			bindView(localInfo, viewHolder, position);
		}

		return convertView;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder,
			int position) {
		if (localInfo.getTitle() != null) {
			viewHolder.tvTitle.setText(localInfo.getTitle());
		}

		if (localInfo.getContent() != null) {
			viewHolder.tvContent.setText(localInfo.getContent());
		}
		if ((position != 0) && (position != localInfos.size() - 1)) {
			if (position == 1) {
				viewHolder.tvContent.setVisibility(View.VISIBLE);
				viewHolder.img.setImageResource(R.drawable.products_arrow2);
			} else if (position > 1 && position < localInfos.size() - 1) {
				viewHolder.tvContent.setVisibility(View.GONE);
				viewHolder.img.setImageResource(R.drawable.products_arrow1);
			}
		}
	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}
}