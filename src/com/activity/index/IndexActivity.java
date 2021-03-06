package com.activity.index;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.activity.CommonActivity;
import com.activity.achievement.AchievementActivity;
import com.activity.balance.BalanceActivity;
import com.activity.balance.UnPaymentDetailActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.InfoDetailActivity;
import com.activity.custome.CustomeIndexActivity;
import com.activity.schedule.ScheduleActivity;
import com.activity.setting.SettingActivity;
import com.activity.shop.ShopActivity;
import com.activity.shop.detail.ShopDetailActivity;
import com.activity.shop.payment.PaymentActivity;
import com.lekoko.sansheng.R;
import com.push.Opration;
import com.sansheng.model.User;
import com.util.DateKeeper;
import com.util.UnitsUtil;
import com.view.CategoryView;

/**
 * @author retryu 主界面Activity
 */
public class IndexActivity extends CommonActivity implements OnClickListener {

	private CategoryView cCompanyAdvisory;
	private CategoryView cAchivementAdvisory;
	private CategoryView cCustomeManager;
	private CategoryView cScheduleAlert;
	private CategoryView cRetailBill;
	private CategoryView cBillQuery;

	public final static String ACTION_NEWS = "news";
	public final static String ACTION_FORM = "form";

	public static String action;
	public static String id;
	private ImageView btnSetting;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_index);
		UnitsUtil.getDestiny(this);
		UnitsUtil.getDpi(this);
		initWidget();
	}
 
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

	 

		super.onResume();
		Intent intent = getIntent();
		if (intent != null) {
			if (action == null) {
				return;
			}
			if (action.equals(ACTION_NEWS)) {

			} else if (action.equals(ACTION_FORM)) {
				intent.setClass(this, UnPaymentDetailActivity.class);
				intent.putExtra(BalanceActivity.META_TYPE, id);
				intent.setAction(BalanceActivity.ACTION_PUSH);
				startActivity(intent);
			}
		}

	}

	public void startTest() {
		String extraStr = "{'caozuokey':product|358}";

		Opration opration = new Opration();
		try {
			JSONObject jsonOpr = new JSONObject(extraStr);
			String value = jsonOpr.getString("caozuokey");

			opration.parse(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent aIntent = null;
		Bundle bundle = new Bundle();

		if (opration.getOpra().equals("order")) {
			aIntent = new Intent(this, PaymentActivity.class);
			// aIntent.setAction(PaymentActivity.ACTION_NEW);
			bundle.putString("id", opration.getNumber());
			aIntent.putExtras(bundle);
		} else if (opration.getOpra().equals("product")) {
			aIntent = new Intent(this, ShopDetailActivity.class);
			aIntent.setAction(ShopDetailActivity.ACTION_NEW);
			bundle.putString("id", opration.getNumber());
			aIntent.putExtras(bundle);
		}
		if (opration.getOpra().equals("product")) {
			aIntent = new Intent(this, InfoDetailActivity.class);
			aIntent.setAction(ShopDetailActivity.ACTION_NEW);
			bundle.putString("id", opration.getNumber());
			aIntent.putExtras(bundle);
		}

		Log.e("debug", "extra" + extraStr);

		Log.e("debug", "push content" + extraStr);
		Bundle b = new Bundle();
		b.putString("type", "push");
		startActivity(aIntent);
	}

	public void initWidget() {

		User a = (User) DateKeeper.getData(this, MYUSER);
		getAesUserName();
		cCompanyAdvisory = (CategoryView) findViewById(R.id.Btn_Company_Info);
		cAchivementAdvisory = (CategoryView) findViewById(R.id.Btn_Achivement);
		cCustomeManager = (CategoryView) findViewById(R.id.Btn_Custome_Manager);
		cScheduleAlert = (CategoryView) findViewById(R.id.Btn_Schedule_Alert);
		cRetailBill = (CategoryView) findViewById(R.id.Btn_Shooping);
		cBillQuery = (CategoryView) findViewById(R.id.Btn_Bill_Query);
		btnSetting = (ImageView) findViewById(R.id.Btn_Setting);
		cCompanyAdvisory.setOnClickListener(this);
		cAchivementAdvisory.setOnClickListener(this);
		cCustomeManager.setOnClickListener(this);
		cScheduleAlert.setOnClickListener(this);
		cRetailBill.setOnClickListener(this);
		cBillQuery.setOnClickListener(this);
		btnSetting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Log.e("debug", "onclick");
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.Btn_Company_Info:
			intent = new Intent(this, CompanyIndexActivity.class);
			startActivity(intent);
			// overridePendingTransition(0, 0);
			break;
		case R.id.Btn_Achivement:
			intent = new Intent(this, AchievementActivity.class);
			startActivity(intent);
			break;

		case R.id.Btn_Custome_Manager:
			intent = new Intent(this, CustomeIndexActivity.class);
			startActivity(intent);
			break;
		case R.id.Btn_Schedule_Alert:
			intent = new Intent(this, ScheduleActivity.class);
			startActivity(intent);
			// overridePendingTransition(0, 0);
			break;
		case R.id.Btn_Shooping:
			intent = new Intent(this, ShopActivity.class);
			startActivity(intent);
			break;
		case R.id.Btn_Bill_Query:
			intent = new Intent(this, BalanceActivity.class);
			startActivity(intent);
			break;
		case R.id.Btn_Setting:
			intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			break;

		}
	}

}
