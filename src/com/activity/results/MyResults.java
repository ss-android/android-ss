package com.activity.results;

import java.util.ArrayList;
import java.util.List;

import com.activity.shop.detail.EvaluateAdapter;
import com.lekoko.sansheng.R;
import com.sansheng.model.Evaluate;
import com.view.ResultsView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MyResults extends Fragment {

	protected View view;
	protected LayoutInflater layoutInflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (View) inflater.inflate(R.layout.layout_results_myresults, null);
		layoutInflater = inflater;
		
		ResultsView resultsview=(ResultsView) view.findViewById(R.id.Results);
//		resultsview.setWidth(320);
		
		// 字体加粗
		TextView Tv_Group_Num = (TextView) view.findViewById(R.id.Tv_Group_Num);
		TextPaint tp_Group_Num = Tv_Group_Num.getPaint();
		tp_Group_Num.setFakeBoldText(true);

		TextView Tv_Current_Num = (TextView) view.findViewById(R.id.Tv_Current_Num);
		TextPaint tp_Current_Num = Tv_Current_Num.getPaint();
		tp_Current_Num.setFakeBoldText(true);

		TextView Tv_Retail_Num = (TextView) view.findViewById(R.id.Tv_Retail_Num);
		TextPaint tp_Retail_Num = Tv_Retail_Num.getPaint();
		tp_Retail_Num.setFakeBoldText(true);

		TextView Tv_Proportion_Num = (TextView) view.findViewById(R.id.Tv_Proportion_Num);
		TextPaint tp_Proportion_Num = Tv_Proportion_Num.getPaint();
		tp_Proportion_Num.setFakeBoldText(true);

		return view;
	}

	// public void initWidget() {
	// lvEvaluate = (ListView) view.findViewById(R.id.Lv_Evaluate);
	// lvEvaluate.setDivider(null);
	// EvaluateAdapter adapter = new EvaluateAdapter(commonActivity, getData());
	// lvEvaluate.setAdapter(adapter);
	// }

	public List<Evaluate> getData() {
		List<Evaluate> evaluates = new ArrayList<Evaluate>();
		for (int i = 0; i < 10; i++) {
			Evaluate e = new Evaluate();
			evaluates.add(e);
		}
		return evaluates;
	}

}
