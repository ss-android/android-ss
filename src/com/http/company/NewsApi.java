package com.http.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.http.HttpUtilOld;
import com.http.response.CommonResponse;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.sansheng.model.Schedule.Type;
import com.util.Constance;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 下午9:26:00 declare:公司新闻接口
 */
public class NewsApi {

	private static String url = Constance.domain
			+ "?g=appserver&m=index&cmd=newss";

	public static List<LocalInfo> getNewss(int index) {
		List<LocalInfo> locaInfos = null;
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		nvs.add(new BasicNameValuePair("pageno", "" + index));
		CommonResponse response = HttpUtilOld.postReq(url, nvs);
		locaInfos = toLocalInfo(response.getResponse());
		return locaInfos;
	}

	public static List<LocalInfo> toLocalInfo(String body) {
		List<LocalInfo> localInfos = null;
		try {
			localInfos = new ArrayList<LocalInfo>();
			JSONObject jsonObject = new JSONObject(body);
			String retCode = jsonObject.getString("retcode");
			String retMsg = jsonObject.getString("retmsg");
			String list = jsonObject.getString("list");
			JSONArray jList = new JSONArray(list);
			for (int i = 0; i < jList.length(); i++) {
				LocalInfo localInfo = new LocalInfo();
				JSONObject jObj = jList.getJSONObject(i);
				String newId = jObj.getString("news_id");
				String newsTitle = jObj.getString("news_title");
				String newsInfo = jObj.getString("news_info");
				String newDate = jObj.getString("news_date");
				String newStatus = jObj.getString("news_status");
				String newsUrl = jObj.getString("url");
				String bImg = jObj.getString("news_bimg");
				String sImg = jObj.getString("news_simg");
				
				 
				localInfo.setNews_bimg(bImg);
				localInfo.setNews_simg(sImg);
				localInfo.setInfoId(newId);
				localInfo.setType(InfoType.news);
				localInfo.setTitle(newsTitle);
				localInfo.setContent(newsInfo);
				localInfo.setData(newDate);
				localInfo.setUrl(newsUrl);
				localInfo.setStatus(newStatus);
				localInfos.add(localInfo);
				Log.e("debug", "index:" + i + "  " + "localInfo:" + localInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return localInfos;
	}

}
