package com.activity.shop.sumary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.activity.shop.address.AddressActivity;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.ShopTypeItem;
import com.view.SumaryView;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午3:31:04 declare:
 */
public class SumaryActivity extends CommonActivity implements OnClickListener {

	public static final String INTENT_PRICE = "price";
	public static final String INTENT_PV = "pv";

	private ShopTypeItem saleItem;
	private ShopTypeItem pvItem;
	private String price;
	private String pv;
	private int type = 0;

	SumaryView sumaryView;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_sumamry);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("复消零售");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		Intent intent = getIntent();
		price = intent.getExtras().getString(INTENT_PRICE);
		pv = intent.getExtras().getString(INTENT_PV);
		sumaryView = (SumaryView) findViewById(R.id.SS2);
		// sumaryView.tvSummaryPrice.setText(price);
		// sumaryView.tvSumamryPV.setText(pv);

		saleItem = (ShopTypeItem) findViewById(R.id.Item_Sale);
		pvItem = (ShopTypeItem) findViewById(R.id.Item_Pv);
		pvItem.unselected();

		saleItem.setOnClickListener(this);
		pvItem.setOnClickListener(this);

		sumaryView.btnSumary.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Item_Sale:
			saleItem.selected();
			pvItem.unselected();
			type = 0;
			break;
		case R.id.Item_Pv:
			saleItem.unselected();
			pvItem.selected();
			type = 1;
			break;
		case R.id.Btn_Sumary:
			Intent intent = new Intent(this, AddressActivity.class);
			startActivity(intent);
			break;
		}

	}
}
