package com.activity.shop.detail;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.sansheng.model.Evaluate;
import com.sansheng.model.LocalInfo;
import com.view.IndicatorView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52 declare:
 */
public class ShopEvaulationFragment extends Fragment {
	private View view;
	private ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvEvaluate;
	private CommonActivity commonActivity;
	private LayoutInflater layoutInflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = (View) inflater.inflate(
				R.layout.fragment_shopping_detail_evaluation, null);
		layoutInflater = inflater;
		commonActivity = (CommonActivity) this.getActivity();
		initWidget();
		return view;
	}

	public void initWidget() {
		lvEvaluate = (ListView) view.findViewById(R.id.Lv_Evaluate);
		lvEvaluate.setDivider(null);
		EvaluateAdapter adapter = new EvaluateAdapter(commonActivity, getData());
		lvEvaluate.setAdapter(adapter);
	} 

	public List<Evaluate> getData() {
		List<Evaluate> evaluates = new ArrayList<Evaluate>();
		for (int i = 0; i < 10; i++) {
			Evaluate e = new Evaluate();
			evaluates.add(e);
		}
		return evaluates;
	}

}
