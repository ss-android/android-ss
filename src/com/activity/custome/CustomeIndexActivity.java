package com.activity.custome;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.activity.CommonActivity;
import com.activity.custome.CustomInfoActivity;
import com.activity.custome.CustomeAdapter;
import com.activity.custome.SearchCustomeAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.model.Contact;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;
import com.view.ListViewSearch;
import com.view.LoadingDilog;
import com.view.SearchView;
import com.view.SideBar;

public class CustomeIndexActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ListViewSearch lvCustome;
	private static final int MSG_NEW_DATA = 1;
	CustomeAdapter customeAdapter;
	private UIHandler uiHandler;
	private LoadingDilog lodingDilog;
	private SearchView searchView;
	private static final int resultCode = 0;
	private CommonActivity commonActivity;
	private Intent intent;

	private ListView lvSearch;
	private SearchCustomeAdapter searchAdapter;
	 

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_select_custome);
		initWidget();
	}

	public void initWidget() {
		commonActivity = this;

		lodingDilog = new LoadingDilog(this);

		lodingDilog.show();
		uiHandler = new UIHandler();
		lvCustome = (com.view.ListViewSearch) findViewById(R.id.ListView_Custome);
		searchView = (com.view.SearchView) findViewById(R.id.SearchView);

		lvSearch = (ListView) findViewById(R.id.Lv_Search);
		searchAdapter = new SearchCustomeAdapter(this);
		lvSearch.setAdapter(searchAdapter);
		customeAdapter = new CustomeAdapter(this);

		lvCustome.setAdapter(customeAdapter);
		intent = getIntent();

		TextView tvDialog = (TextView) findViewById(R.id.alphaText);
		SideBar sideBar = (SideBar) findViewById(R.id.sideBar);
		sideBar.setTextView(tvDialog);
		sideBar.setListView(lvCustome);
		lvCustome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				intent = new Intent(commonActivity, CustomInfoActivity.class);
				Contact contact = customeAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				CustomeIndexActivity.this.setResult(resultCode, intent);
				startActivity(intent);
			}
		});

		lvSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				intent = new Intent(commonActivity, CustomInfoActivity.class);
				Contact contact = searchAdapter.getContacts().get(position);
				Bundle bundle = new Bundle();
				bundle.putSerializable("contact", contact);
				intent.putExtras(bundle);
				startActivity(intent);

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

		searchView.getEtSearch().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.e("text", "change" + s);
				// BaseRequest baseRequest =
				// createRequest(ShopService.PRODUCT_WORD_SEARCH);
				// baseRequest.add("keysword", s.toString());
				// new ShopAsyncTask(activity).execute(baseRequest);
				if (!s.toString().equals("")) {
					searchWord(s.toString());
				} else {
					showCustomList();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setTitle("客户管理");
		headBar.setRightType(BtnType.image);
		headBar.setRightImg(R.drawable.address_edit);
		headBar.setWidgetClickListener(this);
	}

	public void searchWord(String s) {
		lvSearch.setVisibility(View.VISIBLE);
		lvCustome.setVisibility(View.INVISIBLE);
		List<Contact> result = new ArrayList<Contact>();
		List<Contact> contacts = customeAdapter.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			Contact c = contacts.get(i);
			if (c.getPingying().contains(s) || c.getName().contains(s)) {
				result.add(c);
			}
		}
		searchAdapter.setContacts(result);
		searchAdapter.notifyDataSetChanged();
		// lvSearch.setAdapter(result);

	}

	public void showCustomList() {
		lvSearch.setVisibility(View.INVISIBLE);
		lvCustome.setVisibility(View.VISIBLE);

	}

	public List<Contact> getContacts() {
		// ContactUtil contactUtil = new ContactUtil(this);
		// List<Contact> contacts = contactUtil.query();

		List<Contact> contacts = new ArrayList<Contact>();
		Contact c = new Contact();
		c.setName("a1");
		c.setCellphone1("123");
		c.setCellphone2("235");
		c.setId(1);
		contacts.add(c);

		c = new Contact();
		c.setName("a2");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("a3");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("啊软");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("b");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("c");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("d");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e2");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e2");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e3");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e4");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e5");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("e6");
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("m6");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("m66");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("张三");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("李思");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("啊呜");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

		c = new Contact();
		c.setName("qq");
		c.setId(1);
		c.setCellphone1("123");
		c.setCellphone2("235");
		contacts.add(c);

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

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.Img_Right:
			Intent intent = new Intent(this, CustomInfoActivity.class);
			startActivity(intent);
			break;

		case R.id.Btn_Back:
			finish();
			break;
		}
	}

}
