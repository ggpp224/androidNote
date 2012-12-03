package com.example.test1;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private WebView webView;
	private Button jsBtn;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		
		jsBtn = (Button) findViewById(R.id.jsBtn);
		
		jsBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//java调用js函数
				webView.loadUrl(String.format("javascript:java2js()"));
			}
			
		});
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.addJavascriptInterface(new JavascriptInterface(this), "Android");
		WebSettings setting = webView.getSettings() ;
        setting.setJavaScriptEnabled(true) ;
        webView.setWebChromeClient(new WebChromeClient());
		
		webView.loadUrl("file:///android_asset/video.html");
	}

	/**
	 * 定义可供js调用的java接口类,js中调用方式： Android.showToast('aaaa');
	 * @author guopeng
	 */
	class JavascriptInterface{
		Context ctx;
		JavascriptInterface(Context c){
			ctx = c;
		}
		
		public void showToast(String msg){
			Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
		}
	}

}
