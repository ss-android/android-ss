package com.companies;

import java.util.ArrayList;
import java.util.HashMap;

import com.lekoko.sansheng.R;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class companies extends Activity {  
	
	private Button Btn_Return; /**返回按钮**/
	private Button Btn_hot_promotions; /**热点促销**/
	private Button Btn_Latest_news; /**最新公告**/
	private Button Btn_company_news; /**公司新闻**/
	private Button Btn_about; /**公司介绍**/
	private Button Btn_historical_memorabilia; /**历史大事记**/
	private Button company_honor; /**企业荣誉**/
	private Button sansheng_cultural; /**三生文化**/
	private Button chairman_message; /**董事长寄语**/
	private Button sansheng_brands; /**三生品牌**/
	private Button sansheng_industry; /**三生产业**/
	private Button sansheng_in_world; /**三生在全球**/
	private Button Btn_inquiry; /**工作室查询**/
	private Button Btn_competition_results; /**业绩竞赛**/
	private ListView list;
	private Context Ct;
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.companies_info);  
        
        Ct=this;
        Btn_Return=(Button)findViewById(R.id.Btn_Return);
		Btn_Return.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setContentView(R.layout.companies_info);  
			}
		});
		Btn_hot_promotions=(Button)findViewById(R.id.Btn_hot_promotions);
		
		lastNews();
		
		companiesAbout();
		
		historicalMemorabilia();
		
		companyHonor();
		
		sanshengCultural();
		
		chairmanMessage();
		
		sanshengBrands();
		
		sanshengIndustry();
		
		sansheng_in_world=(Button)findViewById(R.id.sansheng_in_world);
		Btn_inquiry=(Button)findViewById(R.id.Btn_inquiry);
		Btn_competition_results=(Button)findViewById(R.id.Btn_competition_results);
        
//        for(int i=0;i<10;i++)  
//        {  
//            HashMap<String, Object> map = new HashMap<String, Object>();  
//            map.put("Tv_title", "健康家生活，自然生活力");  
//            map.put("Tv_content", "文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文! ");  
//            listItem.add(map);  
//        }  
//        
//        CompanDate(listItem,list); 
    }
    
    protected void CompanDate(String strTitle,String strText,ArrayList<HashMap<String, Object>> listItem,ListView listview){
 
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,
            R.layout.companies_about,     
            new String[] {strTitle,strText},   
            new int[] {R.id.Tv_title,R.id.Tv_content}  
        );  
         
        listview.setAdapter(listItemAdapter);  
    }
    
    //总返回事件
    protected void BtnReturn(){
    	Button btn_rtinfo=(Button)findViewById(R.id.Btn_about_return);
        btn_rtinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.companies_info);  
				
				lastNews();
				
				companiesAbout();
				
				historicalMemorabilia();
				
				companyHonor();
				
				sanshengCultural();
				
				chairmanMessage();
				
				sanshengBrands();
				
				sanshengIndustry();
			}
		});
    }
    
    //最新公告
    protected void lastNews(){
    	Btn_Latest_news=(Button)findViewById(R.id.Btn_Latest_news);
		Btn_Latest_news.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<5;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_newstTitle", "公告标题");  
		            map.put("Tv_newstTime", "2013-4-8 10:04:32 ");  
		            map.put("Tv_newstContext", "公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容公告内容"); 
		            listItem.add(map);  
		        }    

				SimpleAdapter listItemAdapter = new SimpleAdapter(Ct,listItem,
			            R.layout.latest_news,     
			            new String[] {"Tv_newstTitle","Tv_newstTime","Tv_newstContext"},   
			            new int[] {R.id.Tv_newstTitle,R.id.Tv_newstTime,R.id.Tv_newstContext}  
			        );
				list.setAdapter(listItemAdapter);  
	            
				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("最新公告");
	            //返回按钮触发的事件
				BtnReturn();
			}
		});
    }
    
    //公司介绍
    protected void companiesAbout(){
    	Btn_about=(Button)findViewById(R.id.Btn_about);
		Btn_about.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<10;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_title", "健康家生活，自然生活力");  
		            map.put("Tv_content", "文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文! ");  
		            listItem.add(map);  
		        }  

	            CompanDate("Tv_title","Tv_content",listItem,list);
	            
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //历史大事记
    protected void historicalMemorabilia(){
    	Btn_historical_memorabilia=(Button)findViewById(R.id.Btn_historical_memorabilia);
		Btn_historical_memorabilia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<10;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_hisTitle", "总投资2.5亿元人民币的三生会议中心正式落成启用");  
		            map.put("Tv_hisTime", "2012年8月 ");  
		            listItem.add(map);  
		        }    

				SimpleAdapter listItemAdapter = new SimpleAdapter(Ct,listItem,
			            R.layout.historical_memorabilia,     
			            new String[] {"Tv_hisTitle","Tv_hisTime"},   
			            new int[] {R.id.Tv_hisTitle,R.id.Tv_hisTime}  
			        );
				list.setAdapter(listItemAdapter);  
	            
				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("历史大事记");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //企业荣誉
    protected void companyHonor(){
    	company_honor=(Button)findViewById(R.id.company_honor);
		company_honor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<3;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>(); 
		            HashMap<String,Object> mapContext=new HashMap<String,Object>();
		            for(int j=0;j<5;j++){
			            mapContext.put("Tv_honorContext"+j, "2012 APEC青年创业家峰会战略合作伙伴");
		            }
		            map.put("Tv_honorTitle", "2012年");  
		            map.put("Tv_honorContext", mapContext);  
		            listItem.add(map);  
		        }    

				SimpleAdapter listItemAdapter = new SimpleAdapter(Ct,listItem,
			            R.layout.companies_honor,     
			            new String[] {"Tv_honorTitle","Tv_honorContext"},   
			            new int[] {R.id.Tv_honorTitle,R.id.Tv_honorContext}  
			        );
				list.setAdapter(listItemAdapter);  
	            
				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("企业荣誉");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //三生文化
    protected void sanshengCultural(){
    	sansheng_cultural=(Button)findViewById(R.id.sansheng_cultural);
		sansheng_cultural.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<10;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_title", "创始人信念");  
		            map.put("Tv_content", "文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文! ");  
		            listItem.add(map);  
		        }  

	            CompanDate("Tv_title","Tv_content",listItem,list);

				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("三生文化");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //董事长寄语
    protected void chairmanMessage(){
    	chairman_message=(Button)findViewById(R.id.chairman_message);
		chairman_message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<10;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_title", "寄语标题");  
		            map.put("Tv_content", "2013-4-8 10:04:32");  
		            listItem.add(map);  
		        }  

	            CompanDate("Tv_title","Tv_content",listItem,list);

				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("董事长寄语");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //三生品牌
    protected void sanshengBrands(){
    	sansheng_brands=(Button)findViewById(R.id.sansheng_brands);
		sansheng_brands.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<10;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("Tv_title", "品牌阐述");  
		            map.put("Tv_content", "文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文! ");  
		            listItem.add(map);  
		        }  

	            CompanDate("Tv_title","Tv_content",listItem,list);

				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("三生品牌");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
    
    //三生产业
    protected void sanshengIndustry(){
    	sansheng_industry=(Button)findViewById(R.id.sansheng_industry);
		sansheng_industry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_about);  
		        list=(ListView)findViewById(R.id.Lv_about); 
				ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
				for(int i=0;i<5;i++)  
		        {  
		            HashMap<String, Object> map = new HashMap<String, Object>(); 
		            map.put("Tv_industryTitle", "三生全球生产研发基地");  
		            map.put("Tv_industryContext", "文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内容文字内"); 
		            listItem.add(map);  
		        }
				
				//缺少图片路劲数据列				

				SimpleAdapter listItemAdapter = new SimpleAdapter(Ct,listItem,
			            R.layout.sansheng_industry,     
			            new String[] {"Tv_industryTitle","Tv_industryContext"},   
			            new int[] {R.id.Tv_industryTitle,R.id.Tv_industryContext}  
			        );
				list.setAdapter(listItemAdapter);  
	            
				TextView Tv_InfoName=(TextView)findViewById(R.id.Tv_InfoName);
				Tv_InfoName.setText("三生产业");
	            //返回按钮触发的事件
	            BtnReturn();
			}
		});
    }
}
