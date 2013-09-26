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
import com.view.BtnTab;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.TabController;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-7 下午10:07:10 declare:
 */
public class AddressActivity extends CommonActivity implements OnClickListener {

	private TabController tabController;

	private ListView lvAddress;
	AddressAdapter addressAdapter;
	private int currentMode = 0;
	private CommonActivity commonActivity;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_address);

		commonActivity = this;
		BtnTab tabHome = (BtnTab) findViewById(R.id.Btn_Home);
		BtnTab tabRoom = (BtnTab) findViewById(R.id.Btn_Room);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("收货地址");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);

		tabController = new TabController();
		tabController.addTab(tabHome);
		tabController.addTab(tabRoom);

		tabHome.setOnClickListener(this);
		tabRoom.setOnClickListener(this);

		lvAddress = (ListView) findViewById(R.id.Lv_Address);
		lvAddress.setDivider(null);
		addressAdapter = new AddressAdapter(this);
		addressAdapter.setAddresses(getData1());
		lvAddress.setAdapter(addressAdapter);
		lvAddress.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Address address = addressAdapter.getAddresses().get(position);
				if (address.getType() == 0) {
					Intent intent = new Intent(commonActivity,
							AddAddressActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(commonActivity,
							RoomAddress.class);
					startActivity(intent);
				}
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

	public List<Address> getData2() {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 1; i++) {
			Address address = new Address();
			address.setType(1);
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

		case R.id.Btn_Home:
			tabController.selected(0);
			if (currentMode == 1) {
				addressAdapter.setAddresses(getData1());
				addressAdapter.notifyDataSetChanged();
				currentMode = 0;
			}
			break;
		case R.id.Btn_Room:
			tabController.selected(1);
			if (currentMode == 0) {
				addressAdapter.setAddresses(getData2());
				addressAdapter.notifyDataSetChanged();
				currentMode = 1;
			}
			break;
		}
	}

}
