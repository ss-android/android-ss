package com.activity.schedule;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.activity.CommonActivity;
import com.activity.custome.CustomeAdapter;
import com.example.sansheng.R;
import com.j256.ormlite.stmt.query.In;
import com.sansheng.model.Contact;
import com.sansheng.model.Schedule;
import com.util.ContactUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ListViewSearch;
import com.view.LodingDilog;

public class SelectCustomeActivity extends CommonActivity {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;
	private UIHandler uiHandler;
	private LodingDilog lodingDilog;
	private com.view.SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_select_custome);
		initWidget();
	}

	public void initWidget() {
		commonActivity = this;
		getSupportActionBar().setTitle("拜访客户");
		getSupportActionBar().hide();
		lodingDilog = new LodingDilog(this);
		lodingDilog.show();
		uiHandler = new UIHandler();
		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightImage(BtnType.empty);
		lvCustome = (com.view.ListViewSearch) findViewById(R.id.ListView_Custome);
		searchView = (com.view.SearchView) findViewById(R.id.SearchView);
		lvCustome.setMySearchView(searchView);
		customeAdapter = new CustomeAdapter(this);
		customeAdapter.setSearchView(searchView);
		lvCustome.setAdapter(customeAdapter);
		intent = getIntent();
		lvCustome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				Contact contact = customeAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				SelectCustomeActivity.this.setResult(resultCode, intent);
				SelectCustomeActivity.this.finish();
			}
		});

		new Thread() {
			public void run() {

				Message msg = new Message();
				msg.what = MSG_NEW_DATA;
				List<Contact> contacts = getContacts();
				msg.obj = contacts;
				uiHandler.sendMessage(msg);
			};
		}.start();
	}

	public List<Contact> getContacts() {
		ContactUtil contactUtil = new ContactUtil(this);
		List<Contact> contacts = contactUtil.query();
		return contacts;
	}

	class UIHandler extends Handler {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_NEW_DATA:
				lodingDilog.dismiss();
				List<Contact> contacts = (List<Contact>) msg.obj;
				customeAdapter.setContacts(contacts);
				break;

			default:
				break;
			}

		}
	}

}
