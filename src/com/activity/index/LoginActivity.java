package com.activity.index;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.activity.CommonActivity;
import com.http.LoginApi;
import com.lekoko.sansheng.R;
import com.sansheng.model.User;
import com.view.BtnTab;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-17 下午9:24:00 declare:
 */
public class LoginActivity extends CommonActivity implements OnClickListener {
	RelativeLayout fl;
	MemberLoginFragemnt memberFragment;
	FragmentManager fm;
	android.support.v4.app.FragmentTransaction ft;

	BtnTab tabMember;
	BtnTab tabShop;
	private int type = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_login);
		fl = (RelativeLayout) findViewById(R.id.FL_Content);
		memberFragment = new MemberLoginFragemnt();
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.add(R.id.FL_Content, memberFragment);
		ft.commit();
		tabMember = (BtnTab) findViewById(R.id.Btn_Member);
		tabShop = (BtnTab) findViewById(R.id.Btn_Shop);
		tabMember.setOnClickListener(this);
		tabShop.setOnClickListener(this);
	}

	public void testLogin() {
		User user = new User();
		user.setUsername("nba001");
		user.setPassword("yftfln");
		LoginApi.Login(user, "1.0|111|android");
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Member:
			setCurrneTab(1);
			break;
		case R.id.Btn_Shooping:
			setCurrneTab(2);
			break;
		case R.id.Btn_Login:
			break;
		}
	}  
	
	
	

	public void setCurrneTab(int index) {
		tabMember.unsleetced();
		tabShop.unsleetced();
		if (index == 1) {
			tabMember.selected();
			type = 0;
		} else {
			tabShop.selected();
			type = 1;
		}
	}
}
