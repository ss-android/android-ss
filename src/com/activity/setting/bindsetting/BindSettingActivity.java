package com.activity.setting.bindsetting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.activity.setting.SettingActivity;
import com.activity.setting.bindsetting.tecent.TecentLoginActivity;
import com.example.sansheng.R;
import com.view.BindItem;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:50:43 declare:
 */
public class BindSettingActivity extends CommonActivity implements
		OnClickListener {

	BindItem itemTecent;
	BindItem itemSina;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub  
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_bind);
		initWidget();
	}

	public void initWidget() {
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle(getStr(R.string.bind_setting));
		headBar.setWidgetClickListener(this);
		itemTecent = (BindItem) findViewById(R.id.Item_Tecent);
		itemSina = (BindItem) findViewById(R.id.Item_Sina);
		itemSina.setOnClickListener(this);
		itemTecent.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.Item_Tecent:
			Log.e("debug", "onclick  tecent");
			intent = new Intent(this, TecentLoginActivity.class);
			startActivity(intent);
			overridePendingTransition(-1, -1);
			break;

		case R.id.Item_Sina:
			Log.e("debug", "onClick  sina");
			break;
		case R.id.Btn_Back:
			back();
		}
	}

	private void back() {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
		overridePendingTransition(-1, -1);
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		back();
	}

}
