package com.activity.company.honor;

import java.util.List;

import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class HonourAdapter extends BaseExpandableListAdapter {

	private List<LocalInfo> localInfos;
	private List<LocalInfo> localInfosTitle;

	private LayoutInflater layoutInflater;

	private Context context = null;

	public HonourAdapter(Context context) {
		this.context = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return localInfosTitle.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		LocalInfo localInfo = localInfosTitle.get(groupPosition);
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_honor, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_Honor);
			convertView.setTag(vHolder);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return localInfosTitle.size();
	}

	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return this.localInfos.get(groupPosition);
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return localInfos.size();
	}

	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LocalInfo localInfo = localInfos.get(groupPosition);
		if (convertView == null) {
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_company_honor_year, null);
			ViewHolder vHolder = new ViewHolder();
			vHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.Tv_HonorYear);
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
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	private class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;

	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}
	public List<LocalInfo> getLocalInfosTitle(){
		return localInfosTitle;
	}
	public void setLocalInfosTitle(List<LocalInfo> LocalInfosTitle) {
		this.localInfosTitle = LocalInfosTitle;
	}

}
