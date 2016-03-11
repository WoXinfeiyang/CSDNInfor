package com.fxj.CSDNInfor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetailActivity extends Activity {
	
	private WebView mWebView;
	private WebSettings settings;
	
	private String urlString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_detail_layout);
		
		this.mWebView=(WebView) findViewById(R.id.detailWebView);
		this.urlString=getIntent().getStringExtra("URL");
		
		dealNewsDetail();
	}
	
	private void dealNewsDetail()
	{
		this.settings=this.mWebView.getSettings();
		this.settings.setUseWideViewPort(true);/*将图片调整到适合webview的大小*/
		this.settings.setJavaScriptEnabled(true);/*启用JavaScript*/
		this.settings.setJavaScriptCanOpenWindowsAutomatically(true);/*支持通过JS打开新窗口*/
		this.settings.setLoadWithOverviewMode(true);/*缩放至屏幕的大小*/
		this.settings.setBuiltInZoomControls(true);/*设置支持缩放*/
		this.settings.setSupportZoom(true);/*支持缩放*/
		
		this.mWebView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				CustomLoadUrl(view, url);
				return true;
			}
			
		});
		CustomLoadUrl(mWebView, urlString);
	}
	
	private void CustomLoadUrl(final WebView view,String url)
	{
		view.loadUrl(url);
	}
	
}
