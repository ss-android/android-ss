package com.activity.shop.address;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午11:18:22 declare:
 */
public class AddAddressActivity extends CommonActivity implements
		OnClickListener {
	private ListView lvAddress;  

	private AddAddressAdapter adapter;
	private CommonActivity commonActivity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_add_address);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("收货地址");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.address_edit);
		headBar.setWidgetClickListener(this);
		lvAddress = (ListView) findViewById(R.id.Lv_Address);
		lvAddress.setDivider(null);
		adapter = new AddAddressAdapter(this);
		adapter.setAddresses(getData1());
		lvAddress.setAdapter(adapter);

		commonActivity = this;
		lvAddress.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Address address = adapter.getAddresses().get(position);
				Intent intent = new Intent(commonActivity,
						EditAddressActivity.class);
				startActivity(intent);
			}
		});
	}

	public List<Address> getData1() {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 3; i++) {
			Address address = new Address();
			address.setType(0);
			addresses.add(address);
		}
		return addresses;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();

			break;

		case R.id.Img_Right:
			Intent intent = new Intent(this, EditAddressActivity.class);
			startActivity(intent);
			break;
		}

	}

}
