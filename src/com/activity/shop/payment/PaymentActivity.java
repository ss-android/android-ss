package com.activity.shop.payment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.shop.ShopActivity;
import com.activity.shop.address.ReapActivity;
import com.http.BaseRequest;
import com.http.CustomFormService;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.CustomeAsynctask;
import com.http.task.FormAsyncTask;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.CustomForm;
import com.sansheng.model.FormDetail;
import com.sansheng.model.TransOrder;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ShopTypeItem;
import com.view.SumaryView;
import com.view.shopListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:31:04 declare:
 */
public class PaymentActivity extends CommonActivity implements OnClickListener {

	public static final String INTENT_PRICE = "price";
	public static final String INTENT_PV = "pv";

	private ShopTypeItem itemTong;
	private ShopTypeItem itemPos;
	private ShopTypeItem itemThird;
	private String price;
	private String pv;
	private int type = 0;
	private String orderCode;

	SumaryView sumaryView;
	AlertDialog.Builder builder;
	private shopListView shopList;
	private TransOrder order;
	private CommonActivity activity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		activity = this;
		setContentView(R.layout.activity_payment);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("支付方式");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		sumaryView = (SumaryView) findViewById(R.id.SS2);
		shopList = (shopListView) findViewById(R.id.Shop_List);
		shopList.bindData(ReapActivity.order.getProductlist());
		// sumaryView.tvSummaryPrice.setText(price);
		// sumaryView.tvSumamryPV.setText(pv);
		TextView TvSumPrice = (TextView) findViewById(R.id.Tv_Sumamry_Number);
		TextView TvSumPv = (TextView) findViewById(R.id.Tv_Sumamry_Pv);
		TvSumPrice.setText(getSumPrice());
		TvSumPv.setText(getSumPv());

		itemTong = (ShopTypeItem) findViewById(R.id.Item_TONG);
		itemPos = (ShopTypeItem) findViewById(R.id.Item_POS);
		itemThird = (ShopTypeItem) findViewById(R.id.Item_Third);
		itemPos.selected();

		itemTong.setOnClickListener(this);
		itemPos.setOnClickListener(this);
		itemThird.setOnClickListener(this);

		sumaryView.btnSumary.setOnClickListener(this);

		itemTong.selected();
		itemPos.unselected();
		itemThird.unselected();
		initData();
	}

	public void initData() {
		order = (TransOrder) getIntent().getExtras().get("order");
		orderCode = (String) getIntent().getExtras().getString("orderCode");
		BaseRequest baseRequest = createRequestWithUserId(CustomFormService.FORM_DETAIL);
		baseRequest.add("userid", getUserId());
		baseRequest.add("orderid", orderCode);
		baseRequest.add("showid", "1");
		new FormAsyncTask(this,null).execute(baseRequest);
	}

	private void pay() {
		ProgressDialogUtil.show(this, "提示", "正在支付", true, true);
		BaseRequest baseRequest = createRequestWithUserId(ShopService.ORDER_PAY);
		baseRequest.add("storeid", order.getStoreid());
		baseRequest.add("runno", orderCode);
		baseRequest.add("paytype", order.getPaytype());
		new ShopAsyncTask(this).execute(baseRequest);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Item_TONG:
			itemTong.selected();
			itemPos.unselected();
			itemThird.unselected();
			type = 0;
			break;
		case R.id.Item_POS:
			itemTong.unselected();
			itemPos.selected();
			itemThird.unselected();
			type = 1;
			break;
		case R.id.Item_Third:
			itemTong.unselected();
			itemPos.unselected();
			itemThird.selected();
			type = 2;
			break;

		case R.id.Btn_Sumary:
			// Intent intent = new Intent(this, ReapActivity.class);
			// startActivity(intent);
			pay();
			break;
		}

	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getHttpCode() != 200)
			return;
		ProgressDialogUtil.close();
		switch (action) {
		case ShopService.ORDER_PAY:
			builder = new Builder(this);
			builder.setMessage("支付成功，是否返回商品主页？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Intent intent = new Intent(activity,
									ShopActivity.class);
							startActivity(intent);
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// activity.finish();

						}
					});
			builder.show();
			break;
		case CustomFormService.FORM_QUERY:
			FormDetail form = (FormDetail) viewCommonResponse.getData();
			Log.e("debug", "form" + form);
			break;

		}
	}

	public void bindFrom(CustomForm form) {

	}
}
