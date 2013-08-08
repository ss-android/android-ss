package com.activity.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.schedule.ScheduleActivity;
import com.companies.companies;
import com.example.sansheng.R;
import com.view.CategoryView;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

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

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_index);
		initWidget();
	}

	public void initWidget() {

		cCompanyAdvisory = (CategoryView) findViewById(R.id.Btn_Company_Info);
		cAchivementAdvisory = (CategoryView) findViewById(R.id.Btn_Achivement);
		cCustomeManager = (CategoryView) findViewById(R.id.Btn_Custome_Manager);
		cScheduleAlert = (CategoryView) findViewById(R.id.Btn_Schedule_Alert);
		cRetailBill = (CategoryView) findViewById(R.id.Btn_Shooping);
		cBillQuery = (CategoryView) findViewById(R.id.Btn_Bill_Query);

		cCompanyAdvisory.setOnClickListener(this);
		cAchivementAdvisory.setOnClickListener(this);
		cCustomeManager.setOnClickListener(this);
		cScheduleAlert.setOnClickListener(this);
		cRetailBill.setOnClickListener(this);
		cBillQuery.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.Btn_Company_Info:
			intent = new Intent(this, CompanyIndexActivity.class);
			overridePendingTransition(0, 0);
			startActivity(intent);
			break;
		case R.id.Btn_Achivement:
			break;

		case R.id.Btn_Custome_Manager:
			break;
		case R.id.Btn_Schedule_Alert:
			intent = new Intent(this, ScheduleActivity.class);
			overridePendingTransition(0, 0);
			startActivity(intent);
			break;
		case R.id.Btn_Shooping:
			break;
		case R.id.Btn_Bill_Query:
			break;
		case R.id.Btn_setting:
			break;

		}
	}

}
