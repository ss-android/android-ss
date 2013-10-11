package com.activity.custome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-9 下午4:47:09 declare:
 */
public class CustomInfoActivity extends CommonActivity implements
		OnClickListener {

	private TextView tvCardNum;
	private TextView tvTime;
	private TextView tvPhone;
	private TextView tvMsg;
	private TextView tvName;

	private View layoutCall;
	private View layoutMsg;
	private View layoutBirthday;

	private static String INTENT_CUSTOM = "CUSTOM";

	private Contact custome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.lekoko.sansheng.R.layout.layout_custom_detail);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("客户信息");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.address_edit);
		headBar.setWidgetClickListener(this);

		tvCardNum = (TextView) findViewById(R.id.Tv_Card_Num);
		tvTime = (TextView) findViewById(R.id.Tv_Time);
		tvPhone = (TextView) findViewById(R.id.Tv_Phone);
		tvMsg = (TextView) findViewById(R.id.Tv_Msg);
		tvName = (TextView) findViewById(R.id.Tv_Custom_Name);

		layoutCall = (View) findViewById(R.id.Layotu_Call);
		layoutMsg = (View) findViewById(R.id.Layotu_Msg);
		layoutBirthday = (View) findViewById(R.id.Layotu_Send_Birthday);

		layoutCall.setOnClickListener(this);
		layoutMsg.setOnClickListener(this);
		layoutBirthday.setOnClickListener(this);
		custome = new Contact();
		custome.setMobilephone("13567124034");
		custome.setName("hdretyri");
		custome.setAddtimer("2013-02-01");
		custome.setCardNum("123");
		bindData(custome);

	}

	public void bindData(Contact contact) {
		if (contact.getCardNum() != null) {
			tvCardNum.setText("卡号:" + custome.getCardNum());
		}
		if (contact.getAddtimer() != null) {
			tvTime.setText("加入时间:" + contact.getAddtimer());
		}
		if (contact.getMobilephone() != null) {
			tvPhone.setText(contact.getMobilephone());
		}
		if (contact.getMobilephone() != null) {
			tvMsg.setText(contact.getMobilephone());
		}
		if (contact.getName() != null) {
			tvName.setText(contact.getName());
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Uri uri;
		Intent intent;
		switch (id) {
		case R.id.Img_Right:
			intent = new Intent(this, CustomeDetailActivity.class);
			startActivity(intent);
			break;
		case R.id.Btn_Back:
			finish();
			break;
		case R.id.Layotu_Call:
			uri = Uri.parse("tel:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
			break;

		case R.id.Layotu_Msg:

			uri = Uri.parse("smsto:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "");
			startActivity(intent);
			break;

		case R.id.Layotu_Send_Birthday:
			uri = Uri.parse("smsto:" + custome.getMobilephone());
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "生日快乐");
			startActivity(intent);
			break;
		}

	}
}
