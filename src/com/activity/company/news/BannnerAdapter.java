package com.activity.company.news;

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
import android.widget.ImageView;

import com.activity.CommonActivity;
import com.activity.company.InfoDetailActivity;
import com.lekoko.sansheng.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.sansheng.model.LocalInfo;
import com.util.AnimateFirstDisplayListener;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 上午11:47:22 declare:
 */
public class BannnerAdapter extends PagerAdapter {

	private List<LocalInfo> news;
	private LayoutInflater layoutInflater;
	private View currentView;

	public CommonActivity activity;

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private DisplayImageOptions options;
	ImageLoader imageLoader;

	public BannnerAdapter(CommonActivity context, List<LocalInfo> ns) {
		this.activity = context;
		news = ns;
		imageLoader = activity.imageLoader;
		options = activity.options;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public List<LocalInfo> getNews() {
		return news;
	}

	public void setNews(List<LocalInfo> news) {
		this.news = news;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (news == null) {
			return 0;
		} else {
			return news.size();
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
		LocalInfo info = news.get(p);
		View view = (View) layoutInflater.inflate(
				R.layout.layout_company_news_banner, null);
		((ViewPager) container).addView(view, 0);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("debug", "view  onclick");
				LocalInfo localInfo = news.get(p);
				Log.e("debug", "url");
				Intent i = new Intent(activity, InfoDetailActivity.class);
				i.putExtra(InfoDetailActivity.TITLE, "xxx详情");
				i.putExtra(InfoDetailActivity.URL, localInfo.getUrl());
				activity.startActivity(i);
			}
		});

		String url = "http://cloud.yofoto.cn" + info.getNews_bimg();

		ImageView img = (ImageView) view.findViewById(R.id.Img);
		imageLoader.displayImage(url, img, options, animateFirstListener);
		return view;

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);

	}

}
