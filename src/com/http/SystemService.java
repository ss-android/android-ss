package com.http;

import java.util.Map;

import org.json.JSONObject;

public class SystemService {
	private ViewCommonResponse response = new ViewCommonResponse();;
	public static final int SYS_FEED_BACK = 1001;
	/** 系列产品 **/
	public static final int SYS_VERSION = 1002;

	public ViewCommonResponse addFeedBack(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FEED_BACK, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public ViewCommonResponse getVersionk(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FEED_BACK, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
