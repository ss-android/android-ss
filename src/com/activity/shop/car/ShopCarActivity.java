package com.activity.shop.car;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.shop.address.ReapActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.activity.shop.sumary.SumaryActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.Product;
import com.sansheng.model.TransOrder;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.SumaryView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-1 下午10:12:02 declare:
 */
public class ShopCarActivity extends CommonActivity implements OnClickListener {

	private ListView lvShopCar;

	private ShopCarAdapter shopCarAdapter;
	/** 0 nommal 1edit mode **/
	private int mode = 0;
	HeadBar headBar;
	SumaryView sumaryView;
	private CommonActivity activity;

	public static boolean needReersh = true;
	private TextView TvSumPrice;
	private TextView TvSumPv;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shop_car);
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		activity = this;
		headBar.setTitle("购物车");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.btn_edit_mode);
		headBar.setWidgetClickListener(this);
		TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		lvShopCar = (ListView) findViewById(R.id.Lv_Shop);

		LayoutInflater layoutInflater = getLayoutInflater();

		sumaryView = (SumaryView) findViewById(R.id.SS);
		sumaryView.tvSummaryPrice = (TextView) sumaryView
				.findViewById(R.id.Tv_Sumamry_Number);
		sumaryView.tvSumamryPV = (TextView) sumaryView
				.findViewById(R.id.Tv_Sumamry_Pv);
		sumaryView.btnSumary = (Button) sumaryView
				.findViewById(R.id.Btn_Sumary);
		shopCarAdapter = new ShopCarAdapter(this);
		lvShopCar.setAdapter(shopCarAdapter);
		// shopCarAdapter.setProducts(getTempData());
		lvShopCar.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Product product = shopCarAdapter.getProducts().get(position);
				Intent intent = new Intent(activity, ShopDetailActivity.class);
				Bundle bundle = new Bundle();
				Brand brand = new Brand();
				brand.setId(product.getPid());
				bundle.putSerializable(ShopDetailActivity.INTNET_PRODUCT, brand);
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});

		sumaryView.btnSumary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shopCarAdapter.getProducts().size() != 0) {
					Intent intent = new Intent(activity, SumaryActivity.class);
					intent.putExtra(SumaryActivity.INTENT_PRICE,
							sumaryView.tvSummaryPrice.getText().toString());
					intent.putExtra(SumaryActivity.INTENT_PV,
							sumaryView.tvSumamryPV.getText().toString());
					TransOrder order = new TransOrder();
					order.setProductlist(shopCarAdapter.getProducts());
					order.setTotalpv(getSumPv());
					order.setTotalamt(getSumPrice());
					order.setUsername(getUserName());
					order.setUbianhao(getUserId());
					order.setSysflag("1");
					intent.putExtra("order", order);
					ReapActivity.needRefersh = true;
					activity.startActivity(intent);
				} else {
					Toast.makeText(activity, "购物车为空 无法结算", Toast.LENGTH_LONG)
							.show();
				}
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (needReersh == true) {
			iniData();
		}
	}

	public List<Product> getTempData() {
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 30; i++) {
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
		case R.id.Img_Right:
			if (mode == 0) {
				mode = 1;
				headBar.setRightImg(R.drawable.btn_shop_gou_bg);
				shopCarAdapter.setEidtMode();
			} else {
				mode = 0;
				headBar.setRightImg(R.drawable.btn_edit_mode);
				shopCarAdapter.setNomlMode();
			}
			break;

		case R.id.Btn_Back:
			finish();
			break;
		}
	}

	public void iniData() {
		ProgressDialogUtil.show(this, "提示", "正在加载数据", true, true);

		BaseRequest request = createRequestWithUserId(ShopService.SHOP_CAR_LIST);
		request.add("pageno", "0");
		request.add("all", "1");
		new ShopAsyncTask(activity).execute(request);
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		switch (action) {
		case ShopService.SHOP_CAR_LIST:
			ProgressDialogUtil.close();
			List<Product> products = (List<Product>) viewCommonResponse
					.getData();
			if (products != null) {
				shopCarAdapter.setProducts(products);
			}
			sum(products);
			break;
		case ShopService.SHOP_CAR_LIST_EDIT:
			List<Product> ps = (List<Product>) viewCommonResponse.getData();
			List<Product> dataProduct = shopCarAdapter.getProducts();
			for (int i = 0; i < dataProduct.size(); i++) {
				Product dp = dataProduct.get(i);
				Product product = shopCarAdapter.findById(dp.getId());
				product.setMun(dp.getMun());
			}

			shopCarAdapter.notifyDataSetChanged();
			break;

		}
	}

	public void sum(List<Product> products) {

		float sumPrice = 0;
		float sumPv = 0;
		if (products != null) {
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				float price = Float.parseFloat(product.getPrice());
				float pv = Float.parseFloat(product.getPv());
				sumPrice += price * product.getMun();
				sumPv += pv * product.getMun();

			}
		}
		DecimalFormat fnum = new DecimalFormat("##0.00");
		TvSumPrice.setText("￥" + fnum.format(sumPrice));
		saveSumPrice(fnum.format(sumPrice));
		saveSumPv("" + fnum.format(sumPv));
		TvSumPv.setText("" + fnum.format(sumPv));
	}

}
