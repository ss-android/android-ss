package com.activity.shop.home;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.activity.CommonActivity;
import com.activity.shop.brand.BrandActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午2:54:20 declare:
 * 
 */
public class BrandAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<Brand> brands;
	private CommonActivity activity;

	public BrandAdapter(CommonActivity activity) {
		this.activity = activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (brands == null) {
			return 0;
		} else {
			return brands.size();
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
			convertView = (View) inflater.inflate(
					R.layout.layout_shop_series_item, null);
		}
		bindView(convertView, position);
		return convertView;
	}

	public void bindView(View view, int position) {

		final Brand brand = brands.get(position);

		ImageView imgOne = (ImageView) view.findViewById(R.id.Img_Row_1);
		ImageView imgTwo = (ImageView) view.findViewById(R.id.Img_Row_2);
		ImageView imgThree = (ImageView) view.findViewById(R.id.Img_Row_3);

		imgOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(activity, BrandActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("brand", brand);
				activity.startActivity(i);

			}
		});
		imgTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(activity, ShopDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("brand", brand);
				activity.startActivity(i);

			}
		});

		imgThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(activity, ShopDetailActivity.class);
				Bundle b = new Bundle();
				b.putSerializable("brand", brand);
				activity.startActivity(i);

			}
		});

	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

}
