package com.activity.shop.brand;

import java.util.List;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午8:35:53 declare:
 */
public class BrandAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private View currentView;

	public Activity activity;

	private List<Product> products;

	public BrandAdapter(CommonActivity context) {
		activity = context;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (products == null) {
			return 0;
		} else {
			return products.size();
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
			convertView = (View) layoutInflater.inflate(
					R.layout.layout_brand_list, null);
		}

		return convertView;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
