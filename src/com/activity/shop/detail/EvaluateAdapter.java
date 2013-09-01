package com.activity.shop.detail;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Evaluate;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-31 下午8:46:45 declare:
 */
public class EvaluateAdapter extends BaseAdapter {

	private List<Evaluate> evaluates;
	private LayoutInflater layoutInflater;
	private View currentView;

	public Activity activity;

	public EvaluateAdapter(Context context, List<Evaluate> ns) {
		evaluates = ns;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		Log.e("debug", "size:" + evaluates.size());
		if (evaluates == null) {
			return 0;
		} else {
			return evaluates.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}   

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.layout_shop_evaulation_item, null);
		}
		return convertView;
	}

}
