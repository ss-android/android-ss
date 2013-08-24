package com.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.http.response.CommonResponse;
import com.sansheng.model.User;
import com.util.Constance;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-18 上午10:49:36 declare:
 */
public class LoginApi extends CommonApi {

	private static String url = Constance.domain
			+ "?g=appserver&m=index&cmd=login";

	public static CommonResponse Login(User user, String deviceInfo) {
		CommonResponse commonResponse = null;
		List<NameValuePair> nvs = toNV(user, deviceInfo);
		String resp = HttpUtil.post(url, nvs);
		commonResponse = getResponse(resp);
		System.out.println(commonResponse);
		return commonResponse;
	}

	public static List<NameValuePair> toNV(User user, String devicesInfo) {
		List<NameValuePair> nvs = new ArrayList<NameValuePair>();
		nvs.add(new BasicNameValuePair("username", user.getUsername()));
		nvs.add(new BasicNameValuePair("password", user.getPassword()));
		nvs.add(new BasicNameValuePair("logintype", "" + user.getLogintype()));
		nvs.add(new BasicNameValuePair("terminalinfo", devicesInfo));
		return nvs;
	}

	public static User getResponseUser(String jsonStr) {
		JSONObject json;
		User user = new User();
		try {
			json = new JSONObject(jsonStr);
			String retmsg = json.getString("retmsg");
			JSONObject jsonUser = json.getJSONObject("userinfo");
			String userid = jsonUser.getString("userid");
			String name = jsonUser.getString("name");
			String shopId = jsonUser.getString("shopid");
			String shopName = jsonUser.getString("shopname");
			String rpv = jsonUser.getString("rpv");
			user.setUserId(userid);
			user.setName(name);
			user.setShopId(shopId);
			user.setShopName(shopName);
			user.setRpv(rpv);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
