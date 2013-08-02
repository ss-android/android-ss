package com.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.sansheng.R;

public class LodingDilog extends Dialog {

	private LayoutInflater layoutInflater;
	private TextView tvContent;

	public LodingDilog(Context context) {
		super(context);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.loading_dialog, null);
		setCancelable(false);
		setContentView(view);
		setTitle(context.getResources().getString(R.string.alert));

	}

	private void initWidget() {
		tvContent = (TextView) findViewById(R.id.Tv_Loading);
	}

	public void setContent(String content) {
		tvContent.setText(content);
	}

}
