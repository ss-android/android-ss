package com.activity.company.quality;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lekoko.sansheng.R;
import com.lekoko.sansheng.R.drawable;
import com.sansheng.model.LocalInfo;

public class QualityAdapter extends BaseExpandableListAdapter {

	private List<LocalInfo> localInfos;

	private LayoutInflater layoutInflater;

	ViewHolder vHolder = null;

	private Drawable draw;

	public QualityAdapter(Context context) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public Button btnClick;
	}

	private void bindView(LocalInfo localInfo, ViewHolder viewHolder) {
		// if (localInfo.getTitle() != null) {
		// viewHolder.tvTitle.setText(localInfo.getTitle());
		// }

		if (localInfo.getContent() != null) {
			viewHolder.tvContent.setText(localInfo.getContent());
		}

	}

	public Drawable getDraw() {
		return draw;
	}
	public void setDraw(Drawable draw){
		this.draw=draw; 
	}

	public List<LocalInfo> getLocalInfos() {
		return localInfos;
	}

	public void setLocalInfos(List<LocalInfo> localInfos) {
		this.localInfos = localInfos;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return localInfos.size();
	}

	@Override
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
					R.layout.layout_company_quality, null);
			vHolder = new ViewHolder();
			// vHolder.tvTitle = (TextView) convertView
			// .findViewById(R.id.Tv_Cultural_Title);
			vHolder.tvContent = (TextView) convertView
					.findViewById(R.id.Tv_QualityContent);
			vHolder.btnClick = (Button) convertView
					.findViewById(R.id.IvBtn_ContentThree);
			if (localInfos.size() - 1 == groupPosition) {
				vHolder.tvContent.setVisibility(View.VISIBLE);
				vHolder.btnClick.setText(null);
				vHolder.btnClick.setCompoundDrawables(null, null, draw, null);
			} else {
				vHolder.tvContent.setVisibility(View.GONE);
			}

			convertView.setTag(vHolder);
		}
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		bindView(localInfo, viewHolder);
		return convertView;
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

}