package com.activity.setting.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.activity.setting.SettingActivity;
import com.example.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:50:07 declare:
 */
public class FeedBackActivity extends CommonActivity implements OnClickListener {
	EditText etFeedBack;

	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_feed_back);
		initWidget();
	}

	public void initWidget() {
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle(getStr(R.string.feed_back));
		headBar.setRightType(BtnType.btn);
		headBar.setBtnRightText(getStr(R.string.send));
		headBar.setWidgetClickListener(this);
		etFeedBack = (EditText) findViewById(R.id.Et_Feed_Bak);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			Intent i = new Intent(this, SettingActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.Btn_Right:
			Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
