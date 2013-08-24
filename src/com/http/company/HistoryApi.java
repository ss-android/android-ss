package com.http.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.http.HttpUtil;
import com.http.response.CommonResponse;
import com.sansheng.model.LocalInfo;
import com.sansheng.model.LocalInfo.InfoType;
import com.sansheng.model.Schedule.Type;
import com.util.Constance;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 下午9:26:00 declare:公司新闻接口
 */
public class HistoryApi {

	private static String url = Constance.domain
			+ "?g=appserver&m=index&cmd=company&comid=15";

	public static List<LocalInfo> getHistories() {
		List<LocalInfo> locaInfos = null;
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		CommonResponse response = HttpUtil.postReq(url, null);
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
				String newsTitle = jObj.getString("title");
				String newsInfo = jObj.getString("content");
				localInfo.setType(InfoType.history);
				localInfo.setTitle(newsTitle);
				localInfo.setContent(newsInfo);
				localInfos.add(localInfo);
				Log.e("debug", "index:" + i + "  " + "localInfo:" + localInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return localInfos;
	}

}
