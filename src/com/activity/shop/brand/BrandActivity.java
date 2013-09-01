package com.activity.shop.brand;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午8:24:11 declare:
 */
public class BrandActivity extends CommonActivity implements OnClickListener {

	ListView lvBrand;
	BrandAdapter brandAdapter;
	private CommonActivity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_brand_list);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);

		headBar.setTitle("御坊堂");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		lvBrand = (ListView) findViewById(R.id.Lv_Brand_My);
		activity = this;
		lvBrand.setDivider(null);
		lvBrand.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(activity, ShopDetailActivity.class);
				activity.startActivity(i);
			}
		});

		brandAdapter = new BrandAdapter(this);
		brandAdapter.setProducts(getProducts());
		lvBrand.setAdapter(brandAdapter);
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();

		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			products.add(product);
		}
		return products;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;

		default:
			break;
		}

	}
}
