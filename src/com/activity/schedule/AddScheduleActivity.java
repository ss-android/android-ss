package com.activity.schedule;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.activity.CommonActivity;
import com.application.CommonApplication;
import com.example.sansheng.R;
import com.sansheng.dao.interfaze.ScheduleDao;
import com.sansheng.db.OrmDateBaseHelper;
import com.sansheng.model.Contact;
import com.sansheng.model.Schedule;
import com.sansheng.model.Schedule.Type;
import com.util.DateUtil;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

public class AddScheduleActivity extends CommonActivity implements
		android.view.View.OnClickListener {

	private ActionBar actionBar;
	private Button btnCustome;
	private Button btnDate;
	private Button btnFinish;
	private EditText etContent;
	private RadioGroup rgType;
	private Schedule schedule;
	private DatePickerDialog dlg;
	private Button btnDelete;
	private CommonActivity commonActivity;
	private HeadBar headBar;
	Contact contact;

	private ScheduleDao scheduleDao;
	private Schedule oldSchedule;
	private int modle;
	private static final int MODEL_EDIT = 1;
	private static final int MODEL_ADD = 2;
	private int tabIndex;

	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		commonActivity = this;
		setContentView(R.layout.activity_add_schedule);
		CommonApplication app = (CommonApplication) getApplication();
		OrmDateBaseHelper ormDateBaseHelper = app.getOrmDateBaseHelper();
		scheduleDao = ormDateBaseHelper.getScheduleDao();
		modle = MODEL_ADD;
		initWidget();
		checkType(getIntent());
	}

	public void checkType(Intent intent) {

		Bundle bundle = intent.getExtras();
		if (bundle == null) {
			return;
		}
		oldSchedule = (Schedule) bundle.get("schedule");
		modle = MODEL_EDIT;
		if (oldSchedule.getCustome_name() != null) {
			btnCustome.setText(oldSchedule.getCustome_name());
		}
		if (oldSchedule.getContent() != null) {
			btnDate.setText(oldSchedule.getData());
		}
		if (oldSchedule.getContent() != null) {
			etContent.setText(oldSchedule.getContent());
		}
		if (oldSchedule.getType() == 1) {
			rgType.check(R.id.RB_Visit);
			tabIndex = 0;
		}
		if (oldSchedule.getType() == 2) {
			rgType.check(R.id.RB_BirthDay);
			tabIndex = 1;
		}
		if (oldSchedule.getType() == 3) {
			rgType.check(R.id.RB_Other);
			tabIndex = 2;
		}
		btnDelete.setVisibility(View.VISIBLE);

		btnDelete.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					scheduleDao.delete(oldSchedule);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				back();
			}

		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			back();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initWidget() {
		schedule = new Schedule();
		tabIndex = 1;
		actionBar = getSupportActionBar();
		actionBar.hide();
		actionBar.setLogo(R.drawable.search);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setTitle(getResources().getString(
				R.string.title_schedule_edit));
		etContent = (EditText) findViewById(R.id.Et_Content);
		btnCustome = (Button) findViewById(R.id.Btn_Custome);
		btnDate = (Button) findViewById(R.id.Btn_Date);
		btnFinish = (Button) findViewById(R.id.Btn_Finish);
		rgType = (RadioGroup) findViewById(R.id.RB_Type);
		btnDelete = (Button) findViewById(R.id.Btn_Delete);
		headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setWidgetClickListener(this);
		headBar.setRightImage(BtnType.empty);
		schedule.setType(Type.visit);
		rgType.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (checkedId == R.id.RB_Visit) {
					schedule.setType(Type.visit);
					tabIndex = 0;
				}
				if (checkedId == R.id.RB_BirthDay) {
					schedule.setType(Type.birthday);
					tabIndex = 1;
				}

				if (checkedId == R.id.RB_Other) {
					schedule.setType(Type.other);
					tabIndex = 2;
				}
				Log.e("debug", "t" + tabIndex);
			}
		});
		btnCustome.setOnClickListener(this);
		btnDate.setOnClickListener(this);
		btnFinish.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.Btn_Custome:
			Intent intent = new Intent(this, SelectCustomeActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.Btn_Back:
			back();
			break;
		case R.id.Btn_Date:
			showDatePicker();
			break;
		case R.id.Btn_Finish:
			try {
				if (modle == MODEL_ADD) {
					if (ValideDate(schedule) == true) {
						scheduleDao.create(schedule);
					} else {
						return;
					}
				} else {
					if (ValideDate(oldSchedule) == true) {
						scheduleDao.update(oldSchedule);
					} else {
						return;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			back();

			break;
		}
	}

	public boolean ValideDate(Schedule schedule) {

		if (schedule.getCustome_id() != 0) {

		} else {
			Toast.makeText(this, getStr(R.string.alert_please_select_custome),
					Toast.LENGTH_SHORT).show();
			return false;
		}

		if (!etContent.getText().toString().equals("")) {
			schedule.setContent(etContent.getText().toString());
		} else {

			Toast.makeText(this, getStr(R.string.alert_please_warm_content),
					Toast.LENGTH_SHORT).show();
			return false;

		}

		if (schedule.getData() == null || schedule.getData().equals("")) {
			Toast.makeText(this, getStr(R.string.alert_please_select_date),
					Toast.LENGTH_SHORT).show();
			return false;
		} else {

		}
		return true;

	}

	public void showDatePicker() {
		final Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date myDate = null;
		if (modle == MODEL_EDIT) {
			try {
				myDate = DateUtil.format.parse(oldSchedule.getData());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				myDate = new Date();
				e.printStackTrace();
			}
		} else {
			myDate = new Date();
		}
		// 创建一个Date实例
		calendar.setTime(myDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		// 获得日历中的 year month day
		dlg = new DatePickerDialog(this, 0, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Log.e("debyg", "day:" + dayOfMonth + "  month:" + monthOfYear
						+ 1 + "  year:" + year);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar day = Calendar.getInstance(Locale.CHINA);
				day.set(year, monthOfYear, dayOfMonth);
				String dayStr = sdf.format(day.getTime());
				if (modle == MODEL_ADD) {
					schedule.setData(dayStr);
				} else {
					oldSchedule.setData(dayStr);
				}
				btnDate.setText(dayStr);
			}
		}, year, month, day);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// (picker is a DatePicker)
			dlg.getDatePicker().setMinDate(calendar.getTimeInMillis());
		} else {
			final int minYear = calendar.get(Calendar.YEAR);
			final int minMonth = calendar.get(Calendar.MONTH);
			final int minDay = calendar.get(Calendar.DAY_OF_MONTH);

			dlg.getDatePicker().init(minYear, minMonth, minDay,
					new OnDateChangedListener() {

						public void onDateChanged(DatePicker view, int year,
								int month, int day) {
							Calendar newDate = Calendar.getInstance();
							newDate.set(year, month, day);

							if (calendar.after(newDate)) {
								view.init(minYear, minMonth, minDay, this);
							}
						}
					});
			Log.w("  ", "API Level < 11 so not restricting date range...");
		}
		dlg.show();
		dlg.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dlg.dismiss();
					}
				});
	}

	public String getContent() {
		String content = etContent.getText().toString();
		return content;
	}

	public void toSelectActivity() {

	}

	@Override
	protected void onActivityResult(int arg0, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, resultCode, data);
		if (resultCode == 0) {
			if (data != null) {
				Bundle bundle = data.getExtras();
				contact = (Contact) bundle.get("contact");
				Log.e("debug", "" + contact);
				if (modle == MODEL_ADD) {
					setContact(schedule, contact);
				} else {
					setContact(oldSchedule, contact);
				}
				btnCustome.setText(contact.getName());
			}
		}
	}

	public void setContact(Schedule schedule, Contact contact) {
		schedule.setCustome_id(contact.getId());
		schedule.setCustome_name(contact.getName());
		schedule.setPhoneNumber(contact.getCellphone1());
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		back();
	}

	private void back() {
		Intent intent = new Intent(this, ScheduleActivity.class);
		intent.putExtra("tabIndex", tabIndex);
		startActivity(intent);
		finish();
	}
}
