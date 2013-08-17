package com.activity.company.news;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lekoko.sansheng.R;

/** 
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 上午11:47:22 declare:
 */ 
public class BannnerAdapter extends PagerAdapter {

	private List<news> news;
	private LayoutInflater layoutInflater;
	private View currentView;

	public BannnerAdapter(Context context,List<news>  ns) {
		news=ns;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public List<news> getNews() {
		return news;
	}

	public void setNews(List<news> news) {
		this.news = news;
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
		
		View view = (View) layoutInflater.inflate(
				R.layout.layout_company_news_banner, null);
		((ViewPager) container).addView(view, 0);
		return view;

	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
		
	}

}
