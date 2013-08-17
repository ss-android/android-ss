package com.companies;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lekoko.sansheng.R;

public class CompaniesNews extends Activity {
	protected Button Btn_NewsReturn;
	protected ListView Lv_CompaniesNews;
	protected TextView Tv_CompaniesNewsTitle;
	protected ImageView Iv_CompaniesImg;
	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.companies_news_tv);  
        Iv_CompaniesImg=(ImageView)findViewById(R.id.Img_companiesNews);
        Tv_CompaniesNewsTitle=(TextView)findViewById(R.id.Tv_headNewsTitle);
        
        Btn_NewsReturn=(Button)findViewById(R.id.Btn_companiesNews_return);
        Btn_NewsReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CompaniesNews.this, companies.class);
				startActivity(intent);
				CompaniesNews.this.finish();
			}
		});
        
        Lv_CompaniesNews=(ListView)findViewById(R.id.Lv_companiesNews);
        ArrayList<HashMap<String, Object>> listHeadItem=new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> map=new HashMap<String,Object>();
        
        Iv_CompaniesImg.setImageDrawable(getResources().getDrawable(R.drawable.u0_normal));
        Tv_CompaniesNewsTitle.setText("最新Android教程发布");
        
        for(int i=0;i<5;i++){
	        map.put("NewsTitle", "重启“单独二胎”政策尚在调研之中");
	        map.put("NewsTime", "2013.12.12");
	        map.put("NewsImg", R.drawable.u0_normal);
//	        map.put("NewsImg", "http://images.china.cn/attachement/jpg/site1000/20130718/001aa0ba48ef1351f00d02.jpg");
	        listHeadItem.add(map);
        }
        
        SimpleAdapter spAdapter=new SimpleAdapter(this,listHeadItem,R.layout.companies_news,
        		new String[]{"NewsTitle","NewsTime","NewsImg"},
        		new int[]{R.id.Tv_newsTitle,R.id.Tv_creatTime,R.id.Iv_newsImg});
        Lv_CompaniesNews.setAdapter(spAdapter);
	}
}
