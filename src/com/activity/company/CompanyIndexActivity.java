package com.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.activity.CommonActivity;
import com.activity.company.announcement.AnnouncementActivity;
import com.activity.company.brands.BrandsActivity;
import com.activity.company.community.CommunityActivity;
import com.activity.company.cultural.CulturalActivity;
import com.activity.company.honor.Honour_Activity;
import com.activity.company.introduce.IntroduceAcitity;
import com.activity.company.news.NewsActivity;
import com.activity.company.produce.ProduceActivity;
import com.activity.company.quality.QualityActivity;
import com.activity.company.sale.SaleActivity;
import com.activity.index.IndexActivity;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * 
 * 公司主界面
 * 
 * @author retryu
 * 
 */
public class CompanyIndexActivity extends CommonActivity implements
		OnClickListener {
	// 顶部HeadBar
	HeadBar headBar;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_index);
		Button btnAnnouncement = (Button) findViewById(R.id.Btn_Announcement);
		btnAnnouncement.setOnClickListener(this);

		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.company_info));
		headBar.setWidgetClickListener(this);
		headBar.setRightType(BtnType.empty);
		initWidget();
	}
  
	public void initWidget() {

		Button btnNews = (Button) findViewById(R.id.Btn_News);
		Button btnSlaes = (Button) findViewById(R.id.Btn_Sales);

		Button btnIntroduce = (Button) findViewById(R.id.Btn_Company_Indroduction);
		Button btnHistory = (Button) findViewById(R.id.Btn_Histoty);
		Button btnHornor = (Button) findViewById(R.id.Btn_Company_Honor);

		Button btnCulture = (Button) findViewById(R.id.Btn_Company_Culture);
		Button btnBrand = (Button) findViewById(R.id.Btn_Brands);
		// Button btnChairman = (Button) findViewById(R.id.Btn_Chairman);

		Button btnIndustry = (Button) findViewById(R.id.Btn_Company_Community);
		Button btnWorld = (Button) findViewById(R.id.Btn_Company_Quality);
		Button btnProduce = (Button) findViewById(R.id.Btn_Company_Produce);

		btnNews.setOnClickListener(this);
		btnSlaes.setOnClickListener(this);
		btnIntroduce.setOnClickListener(this);
		btnHistory.setOnClickListener(this);
		btnHornor.setOnClickListener(this);
		btnCulture.setOnClickListener(this);
		btnBrand.setOnClickListener(this);
		// btnChairman.setOnClickListener(this);
		btnIndustry.setOnClickListener(this);
		btnWorld.setOnClickListener(this);
		btnProduce.setOnClickListener(this);

	}

	// // 公司主界面商各个按钮的的响应事件
	// @Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i;
		switch (id) {

		case R.id.Btn_Announcement:
			i = new Intent(this, AnnouncementActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_News:
			i = new Intent(this, NewsActivity.class);
			startActivity(i);
			break;

		case R.id.Btn_Sales:
			i = new Intent(this, SaleActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Company_Indroduction:
			i = new Intent(this, IntroduceAcitity.class);
			startActivity(i);
			break;
		case R.id.Btn_Histoty:
			break;

		case R.id.Btn_Company_Honor:
			i=new Intent(this,Honour_Activity.class);
			startActivity(i);
			break;
		case R.id.Btn_Brands:
			i = new Intent(this, BrandsActivity.class);
			startActivity(i);
			break;
		// case R.id.Btn_Chairman:
		// break;
		case R.id.Btn_Company_Culture:
			i = new Intent(this, CulturalActivity.class);
			startActivity(i);
			break;

		case R.id.Btn_Company_Community:
			i = new Intent(this, CommunityActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Company_Quality:
			i = new Intent(this, QualityActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Company_Produce:
			i = new Intent(this, ProduceActivity.class);
			startActivity(i);
			break;
		case R.id.Btn_Back:
			i = new Intent(this, IndexActivity.class);
			startActivity(i);
			finish();
			break;
		}
	}
}
