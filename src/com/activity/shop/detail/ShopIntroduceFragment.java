package com.activity.shop.detail;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.sansheng.model.LocalInfo;
import com.view.IndicatorView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52 declare:
 */
public class ShopIntroduceFragment extends Fragment {
	private View view;
	private ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvHealth;
	private LayoutInflater layoutInflater;

	private TextView tvIntroduce;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		layoutInflater = inflater;
		view = (View) inflater.inflate(
				R.layout.fragment_shopping_detail_introduce, null);
		initWidget();
		return view;
	}

	public void initWidget() {
		tvIntroduce = (TextView) view.findViewById(R.id.Tv_Introduce);
	}

	public void update(String content) {
		if (content != null) {
			String htmlStr= Html.fromHtml(content).toString();
			
			tvIntroduce.setText(htmlStr);
		}
	}

}
