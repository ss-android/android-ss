package com.activity.shop.address;

import android.R.integer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午1:03:31 declare:
 */
public class RoomAddress extends CommonActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_address_room);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("选择则工作室");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		default:
			break;
		}

	}
}
