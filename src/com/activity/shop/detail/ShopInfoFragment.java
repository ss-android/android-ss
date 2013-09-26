package com.activity.shop.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Adverst;
import com.sansheng.model.Brand;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.Product;
import com.util.ProgressDialogUtil;
import com.view.HorizontalListView;
import com.view.IndicatorView;
import com.view.ShopEditDialog;
import com.view.ShopEditDialog.onDissmissListner;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 上午10:24:52
 * 
 *          declare: 商品基本信息界面
 */
public class ShopInfoFragment extends Fragment implements OnClickListener {
	private View view;
	private static ViewPager viewPager;
	private IndicatorView indicatorView;
	private ListView lvHealth;
	private LayoutInflater layoutInflater;
	private HorizontalListView gallery;
	public static CommonActivity commonActivity;
	private EditText etNum;
	private Button btnAdd;
	private static Product product;
	private ShopEditDialog shopEditDialog;
	ShopInfoFragment shopInfoFragment;
	GallgerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		shopInfoFragment = this;
		view = (View) inflater.inflate(R.layout.fragment_shopping_detail_info,
				null);
		layoutInflater = inflater;

		initWidget();
		return view;
	}

	public void update(Product product) {
		this.product = product;
		TextView tvName = (TextView) view.findViewById(R.id.Tv_Title);
		TextView tvPrice = (TextView) view.findViewById(R.id.Tv_Price);
		TextView tvPv = (TextView) view.findViewById(R.id.Tv_Pv);
		TextView tvNumber = (TextView) view.findViewById(R.id.Tv_Number);
		TextView tvFormat = (TextView) view.findViewById(R.id.Tv_Format);
		TextView tvSumary = (TextView) view.findViewById(R.id.WebView_Shop);

		// 更新产品图片
		if (product != null) {
			adapter.setUrls(product.getImgs());
			adapter.notifyDataSetChanged();
		}
		if (product.getSummary() != null) {
			tvSumary.setText(Html.fromHtml(product.getSummary()));
		}
		etNum.setText("" + product.getMun());
		if (product.getTitle() != null) {
			tvName.setText(product.getTitle());
		}
		if (product.getPrice() != null) {
			tvPrice.setText("￥" + product.getPrice());
		}
		if (product.getPv() != null) {
			tvPv.setText(product.getPv() + "pv");
		}
		if (product.getNumber() != null) {
			tvNumber.setText(product.getNumber());
		}
		if (product.getFormat() != null) {
			tvFormat.setText(product.getFormat());
		}

	}

	public void addShopCar() {
		ProgressDialogUtil.show(commonActivity, "提示", "正在添加", true, true);
		BaseRequest request = commonActivity
				.createRequest(ShopService.PRODUCT_ADD);
		request.add("userid", commonActivity.getUserId());
		request.add("pid", product.getPid());
		request.add("number", product.getNumber());
		request.add("mun", etNum.getText().toString());
		new ShopAsyncTask(commonActivity).execute(request);
	}

	public void setCarMun(int count) {

	}

	public void initWidget() {
		gallery = (HorizontalListView) view.findViewById(R.id.gallery);
		etNum = (EditText) view.findViewById(R.id.Et_Number);
		btnAdd = (Button) view.findViewById(R.id.Btn_Add);
		etNum.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		adapter = new GallgerAdapter(commonActivity);
		String[] urls = { "1", "1", "3", "1", "1", "3", "1", "1", "3" };
		adapter.setUrls(urls);
		gallery.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Add:

			addShopCar();
			break;

		case R.id.Et_Number:
			shopEditDialog = new ShopEditDialog(getActivity());
			shopEditDialog.show(product);

			shopEditDialog.setOnDissmissListner(new onDissmissListner() {

				@Override
				public void OnDissMiss(Product product) {
					shopInfoFragment.product = product;
					update(product);
				}

			});
			shopEditDialog.getBtnFinish().setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							etNum.setText(""
									+ shopEditDialog.getProduct().getMun());
							shopEditDialog.dismiss();
							addShopCar();
						}
					});
			break;
		}
	}

}
