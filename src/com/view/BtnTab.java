package com.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class BtnTab extends Button {

	private int colorSelected = R.color.holo_blue_bright;
	private int colorunSelected = R.color.white;

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
		if (selected == true) {
			selected();
		} else {
			unsleetced();
		}

	}

	public void selected() {
		setBackgroundResource(colorSelected);
	}

	public void unsleetced() {
		setBackgroundResource(colorunSelected);
	}

}
