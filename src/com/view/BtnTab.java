package com.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class BtnTab extends Button {

	private String colorSelected = "#cccccc";
	private String colorunSelected = "#ffffff";

	public BtnTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundColor(Color.parseColor("#ffffff"));
	}

	public void selected() {
		setBackgroundColor(Color.parseColor(colorSelected));
	}

	public void unSleetced() {
		setBackgroundColor(Color.parseColor(colorunSelected));
	}

}
