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
		this.settings.setUseWideViewPort(true);/*��ͼƬ�������ʺ�webview�Ĵ�С*/
		this.settings.setJavaScriptEnabled(true);/*����JavaScript*/
		this.settings.setJavaScriptCanOpenWindowsAutomatically(true);/*֧��ͨ��JS���´���*/
		this.settings.setLoadWithOverviewMode(true);/*��������Ļ�Ĵ�С*/
		this.settings.setBuiltInZoomControls(true);/*����֧������*/
		this.settings.setSupportZoom(true);/*֧������*/
		
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
