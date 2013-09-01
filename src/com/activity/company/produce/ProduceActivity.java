package com.activity.company.produce;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.company.CompanyIndexActivity;
import com.activity.company.quality.QualityAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.LocalInfoDao;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class ProduceActivity extends CommonActivity implements OnClickListener {
	private LocalInfoDao localInfoDao;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_produce);
		localInfoDao = getOrmDateBaseHelper().getLocalInfoDao();
		List<LocalInfo> localInfos = null;

		localInfos = localInfoDao.getLoclInfosByType(InfoType.produce);

		ListView lvQuality = (ListView) findViewById(R.id.Lv_Produce);
		ProduceAdapter produceAdapter = new ProduceAdapter(this);
		produceAdapter.setLocalInfos(localInfos);
		lvQuality.setAdapter(produceAdapter);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.produce_introduce));
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		lvQuality.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView txt = (TextView) arg1
						.findViewById(R.id.Tv_CenterContent);
				ImageView img = (ImageView) arg1
						.findViewById(R.id.Iv_CenterImg);
				if (txt.getVisibility() > 0) {
					txt.setVisibility(View.VISIBLE);
					img.setImageResource(R.drawable.products_arrow2);

				} else {
					txt.setVisibility(View.GONE);
					img.setImageResource(R.drawable.products_arrow1);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i = null;
		switch (id) {
		case R.id.Btn_Back:
			i = new Intent(this, CompanyIndexActivity.class);
			startActivity(i);
			finish();
			break;

		default:
			break;
		}
	}

}