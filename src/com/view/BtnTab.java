package com.view;

import android.R;
import android.R.integer;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class BtnTab extends Button {

	private int colorSelected = R.color.holo_blue_bright;
	private int colorunSelected = R.color.white;
	private String text_select_color;
	private String text_unselect_color;

	public BtnTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				com.lekoko.sansheng.R.styleable.Tab);
		colorSelected = a.getResourceId(
				com.lekoko.sansheng.R.styleable.Tab_select_color, -1);
		colorunSelected = a.getResourceId(
				com.lekoko.sansheng.R.styleable.Tab_unselect_color, -1);
		boolean selected = a.getBoolean(
				com.lekoko.sansheng.R.styleable.Tab_selected, false);
		text_select_color = a
				.getString(com.lekoko.sansheng.R.styleable.Tab_select_text_color);
		text_unselect_color = a
				.getString(com.lekoko.sansheng.R.styleable.Tab_unselect_text_color);
		if (selected == true) {
			selected();
		} else {
			unsleetced();
		}

	}

	public void selected() {
		setBackgroundResource(colorSelected);
		if (text_select_color != null) {
			setTextColor(Color.parseColor(text_select_color));
		}
	}

	public void unsleetced() {
		setBackgroundResource(colorunSelected);
		if (text_unselect_color != null) {
			setTextColor(Color.parseColor(text_unselect_color));
		}
	}

	public void setBtnTitle(String title) {
		setText(title);
	}
}
