package com.szy.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity
{
	WebView mWebView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Activity activity = this;
		mWebView = (WebView) findViewById(R.id.webview);

		mWebView.getSettings().setJavaScriptEnabled(true);   
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   
		mWebView.setHorizontalScrollBarEnabled(false);   
		mWebView.getSettings().setSupportZoom(true);   
		mWebView.getSettings().setBuiltInZoomControls(true);   
		mWebView.setInitialScale(70);   
		mWebView.setHorizontalScrollbarOverlay(true);  

		//WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等 
		mWebView.setWebChromeClient(new WebChromeClient()
		{
			public void onProgressChanged(WebView view, int progress)
			{
				activity.setTitle("Loading...");
				activity.setProgress(progress*100);
				if (progress==100)
				{
					activity.setTitle("Loading OK!");
				}
			}
		});
		
		//WebViewClient就是帮助WebView处理各种通知、请求事件的
		mWebView.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				Log.i("MainActivity", description);
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
		
		mWebView.loadUrl("http://www.baidu.com");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
		{
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
