package com.activity.index;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activity.CommonActivity;
import com.http.LoginApi;
import com.http.response.CommonResponse;
import com.lekoko.sansheng.R;
import com.sansheng.dao.interfaze.UserDao;
import com.sansheng.model.User;
import com.util.AESOperator;
import com.util.DeviceInfo;
import com.view.LoadingDilog;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-19 下午5:12:16 declare:
 */
public class ShopLoginFragment extends Fragment implements OnClickListener {
	View view;
	private User user;
	private EditText etName;
	private EditText etPassWord;
	private Button btnLogin;
	private static final int MSG_TOAMIN = 2;
	private static final int MSG_MESSAGE = 1;
	private UiHandler uiHandler;
	private CommonActivity activity;
	LoadingDilog ldialog;
	private UserDao userDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (View) inflater.inflate(R.layout.fragment_login_shop, null);
		activity = (CommonActivity) this.getActivity();
		userDao = activity.getOrmDateBaseHelper().getUserDaoImple();

		initWidget();
		return view;
	}

	public void initWidget() {
		uiHandler = new UiHandler();
		btnLogin = (Button) view.findViewById(R.id.Btn_Login);
		btnLogin.setOnClickListener(this);
		etName = (EditText) view.findViewById(R.id.Et_User);
		etPassWord = (EditText) view.findViewById(R.id.Et_Pass);
	}

	public User getUser() {
		User u = new User();
		try {
			u.setUsername(etName.getText().toString());
			u.setLogintype(1);
			u.setPassword(etPassWord.getText().toString());
			u.setTerminalinfo(DeviceInfo.getInfo(activity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("debug", u.toString());
		return u;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Login:
			user = getUser();
			if (localCheckUser(user) == false) {
				return;
			}
			ldialog = new LoadingDilog(activity);
			ldialog.show();
			new Thread() {
				public void run() {
					encode();

					CommonResponse resp = LoginApi.Login(user,DeviceInfo.getInfo(activity));
					if (checkUser(resp) == true) {

						try {
							userDao.create(user);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Message msg = new Message();
						msg.what = MSG_TOAMIN;
						uiHandler.sendMessage(msg);
					}
				}

			}.start();

			break;

		default:
			break;
		}

	}

	private void encode() {
		try {
			String etStr = etPassWord.getText().toString();
			String password = AESOperator.getInstance().encrypt(etStr);
			user.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	/**
	 * @param u
	 *            本地验证
	 */
	public boolean localCheckUser(User u) {
		if (u.getUsername() == null || u.getUsername().equals("")) {
			Toast.makeText(getActivity(),
					getString(R.string.alert_user_cant_be_null),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (u.getPassword() == null || u.getPassword().equals("")) {
			Toast.makeText(getActivity(),
					getString(R.string.alert_password_cant_be_null),
					Toast.LENGTH_SHORT).show();
			return false;
		}

		return true;
	}

	public boolean checkUser(CommonResponse resp) {

		Message msg = new Message();
		msg.what = MSG_MESSAGE;

		if (resp.getStateCode() == -1) {
			msg.obj = getString(R.string.alert_login_type_err);
			uiHandler.sendMessage(msg);
			return false;
		}
		if (resp.getStateCode() == -2) {
			msg.obj = getString(R.string.alert_pass_user_err);
			uiHandler.sendMessage(msg);
			return false;
		}
		if (resp.getStateCode() == -2) {
			msg.obj = getString(R.string.alert_pass_user_err);
			uiHandler.sendMessage(msg);
			return false;
		}
		return true;
	}

	class UiHandler extends Handler {

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			int what = msg.what;
			switch (what) {
			case MSG_MESSAGE:
				String msgStr = (String) msg.obj;
				Toast.makeText(getActivity(), msgStr, Toast.LENGTH_SHORT)
						.show();
				ldialog.dismiss();
				break;

			case MSG_TOAMIN:
				ldialog.dismiss();
				Intent i = new Intent(activity, IndexActivity.class);
				startActivity(i);
				activity.finish();
				break;
			}

		}
	}

}
