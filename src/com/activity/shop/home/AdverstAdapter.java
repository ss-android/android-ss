package com.activity.shop.home;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.activity.company.InfoDetailActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Adverst;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 上午11:47:22 declare:
 */
public class AdverstAdapter extends PagerAdapter {

	private List<Adverst> ads;
	private LayoutInflater layoutInflater;
	private View currentView;

	public Activity activity;

	public AdverstAdapter(Context context, List<Adverst> ns) {
		ads = ns;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public List<Adverst> getNews() {
		return ads;
	}

	public void setNews(List<Adverst> news) {
		this.ads = news;
	}

	@Override
	public int getCount() {
		if (ads == null) {
			return 0;
		} else { 
			return ads.size();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		currentView = (View) object;
		return view.equals(object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final int p = position;
		View view = (View) layoutInflater.inflate(
				R.layout.layout_shop_ad_banner, null);
		((ViewPager) container).addView(view, 0);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 
			}
		});
		return view;

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);

	}

}
