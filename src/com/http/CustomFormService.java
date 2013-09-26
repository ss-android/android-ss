package com.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.sansheng.model.CustomForm;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-20 上午11:01:23 declare:
 */
public class CustomFormService {

	private ViewCommonResponse response = new ViewCommonResponse();;

	/**
	 * 2.5.1、 工作室查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryForm(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_FORM_QUERY, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			List<CustomForm> customForms = new ArrayList<CustomForm>();
			customForms = (ArrayList<CustomForm>) JsonUtil.json2ObList(
					json.toString(), "querylist", CustomForm.class);
			response.setData(customForms);
			System.out.println(customForms);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.5.2、 工作室详情查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryFormDetail(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.doGet(
				BaseNetService.URL_FORM_DETAIL, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());
			response = JsonUtil.json2Object(httpCommonResponse.getResponse(),
					CustomForm.class, "detailinfo");
			CustomForm customForm = (CustomForm) response.getData();
			System.out.println(customForm);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * 2.5.3、 工作室详情查询接口
	 * 
	 * @param paramsm
	 * @return
	 */
	public ViewCommonResponse queryFormComment(Map<String, String> params) {
		HttpCommonResponse httpCommonResponse = HttpUtil.post(
				BaseNetService.URL_FROM_COMMENT, params);
		response.setHttpCode(httpCommonResponse.getStateCode());

		try {
			JSONObject json = new JSONObject(httpCommonResponse.getResponse());
			response = JsonUtil.commonParser(json.toString());

			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
