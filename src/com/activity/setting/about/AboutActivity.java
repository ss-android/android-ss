package com.activity.setting.about;

import android.os.Bundle;

import com.activity.CommonActivity;
import com.example.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午12:51:37 declare:
 */
public class AboutActivity extends CommonActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_about);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);
		headBar.setTitle(getStr(R.string.about_sansheng_title));
	}

}
