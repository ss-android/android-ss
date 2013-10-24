package com.activity.shop.address;

import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.http.BaseRequest;
import com.http.ShopService;
import com.http.ViewCommonResponse;
import com.http.task.ShopAsyncTask;
import com.lekoko.sansheng.R;
import com.sansheng.model.Brand;
import com.sansheng.model.Room;
import com.util.DateKeeper;
import com.util.ProgressDialogUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 下午1:03:31 declare:
 */
public class RoomAddressActivity extends CommonActivity implements
		OnClickListener {

	private TextView tvPhone;
	private TextView tvAddress;
	private TextView tvMaster;
	private Button btnSure;
 
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_address_room);
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("选择则工作室");
		headBar.setRightType(BtnType.empty);
		headBar.setWidgetClickListener(this);
		tvPhone = (TextView) findViewById(R.id.Tv_Room_Phone);
		tvAddress = (TextView) findViewById(R.id.Tv_Room_Address);
		tvMaster = (TextView) findViewById(R.id.Tv_Room_Master);
		btnSure = (Button) findViewById(R.id.Btn_Sure);
		btnSure.setOnClickListener(this);
	}

	public void search(String id, String name) {
		ProgressDialogUtil.show(this, "提示", "正在查询", true, true);
		BaseRequest baseRequest = createRequestWithUserId(ShopService.ROOM_ADDRESS);
		baseRequest.add("shopid", id);
		baseRequest.add("shopname", name);
		new ShopAsyncTask(this).execute(baseRequest);
	}

	@Override
	public void refresh(ViewCommonResponse viewCommonResponse) {
		// TODO Auto-generated method stub
		super.refresh(viewCommonResponse);
		int action = viewCommonResponse.getAction();
		if (viewCommonResponse.getMsgCode() != 0)
			return;
		ProgressDialogUtil.close();
		switch (action) {
		case ShopService.ROOM_ADDRESS:
			Room room = (Room) viewCommonResponse.getData();
			bindData(room);
			saveRoom(room);
			DateKeeper.saveData(this, "Room", room);
			ReapActivity.needRefersh=true;
			Room r2 = (Room) DateKeeper.getData(this, "Room");
			System.out.println(r2);
			break;

		}
	}

	public void bindData(Room room) {
		if (room.getShopcall() != null) {
			tvPhone.setText(room.getShopcall());
		}
		if (room.getShopadds() != null) {
			tvAddress.setText("地址:" + room.getShopadds());
		}
		if (room.getShopname() != null) {
			tvMaster.setText("店长:" + room.getShopname());
		}
	}

	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Back:
			finish();
			break;

		case R.id.Btn_Sure:
			search("nba001", "不能发货，公司内部测试用");
			break;
		}

	}
}
