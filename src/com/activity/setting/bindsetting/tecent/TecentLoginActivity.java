package com.activity.setting.bindsetting.tecent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.sax.RootElement;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.activity.CommonActivity;
import com.example.sansheng.R;
import com.tencent.weibo.beans.OAuth;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv1.OAuthV1;
import com.view.HeadBar;
import com.view.HeadBar.BtnType;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-9 下午4:48:09 declare:
 */
public class TecentLoginActivity extends CommonActivity {
	public final static int RESULT_CODE = 1;
	private static final String TAG = "OAuthV1AuthorizeWebView";
	private OAuthV1 oAuth;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_setting_bind_tecent);

		HeadBar headBar = (HeadBar) findViewById(R.id.Head_Bar);
		headBar.setRightType(BtnType.empty);

		
		
		
		WebView webView = (WebView) findViewById(R.id.WebView_Tecetnt);
		Intent intent = this.getIntent();
		oAuth = new OAuthV1();
		oAuth.setOauthToken("123");

		String urlStr = OAuthConstants.OAUTH_V1_AUTHORIZE_URL + "?oauth_token="
				+ oAuth.getOauthToken();
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(true);
		webView.requestFocus();
		webView.loadUrl(urlStr);
		System.out.println(urlStr.toString());
		Log.i(TAG, "WebView Starting....");
		WebViewClient client = new WebViewClient() {
			/**
			 * 回调方法，当页面开始加载时执行
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				Log.i(TAG, "WebView onPageStarted...");
				Log.i(TAG, "URL = " + url);
				if (url.indexOf("checkType=verifycode") != -1) {
					int start = url.indexOf("checkType=verifycode&v=") + 23;
					String verifyCode = url.substring(start, start + 6);
					oAuth.setOauthVerifier(verifyCode);
					Intent intent = new Intent();
					intent.putExtra("oauth", oAuth);
					setResult(RESULT_CODE, intent);
					view.destroyDrawingCache();
					finish();
				}
				super.onPageStarted(view, url, favicon);
			}

		};
		webView.setWebViewClient(client);

	}

}
