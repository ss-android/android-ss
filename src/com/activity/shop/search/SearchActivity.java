package com.activity.shop.search;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Product;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.SearchView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午2:17:49 declare:
 */
public class SearchActivity extends CommonActivity implements OnClickListener {

	private SearchView searchView;
	private ListView lvShop;
	ShopSearchAdapter shopSearchAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shop_search_activity);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("搜索");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		lvShop = (ListView) findViewById(R.id.Lv_Shop);
		searchView = (SearchView) findViewById(R.id.SearchView);
		searchView.btnSearchView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("debug", "search" + searchView.getContent());
				shopSearchAdapter.setProducts(getTempData());
				shopSearchAdapter.notifyDataSetChanged();
			}
		});
		shopSearchAdapter = new ShopSearchAdapter(this);

		lvShop.setAdapter(shopSearchAdapter);

	}

	public List<Product> getTempData() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 0; i++) {
			Product product = new Product();
			product.setName("舒伯赖氨基酸洁面皂");
			product.setNumber("1");
			product.setPrice("120");
			product.setPv("89");
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
