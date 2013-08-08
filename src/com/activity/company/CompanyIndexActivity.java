package com.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.activity.CommonActivity;
import com.activity.company.announcement.AnnouncementActivity;
import com.example.sansheng.R;

public class CompanyIndexActivity extends CommonActivity implements
		OnClickListener {
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_company_index);
		Button btnAnnouncement = (Button) findViewById(R.id.Btn_Announcement);
		btnAnnouncement.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent i;
		switch (id) {

		case R.id.Btn_Announcement:
			i = new Intent(this, AnnouncementActivity.class);
			startActivity(i);
			finish();
			break;
		}
	}

}
