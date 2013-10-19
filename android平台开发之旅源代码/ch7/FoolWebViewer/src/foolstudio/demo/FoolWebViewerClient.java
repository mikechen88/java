package foolstudio.demo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

//自定义浏览器客户端
public class FoolWebViewerClient extends WebViewClient {
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return (true);
	}
};
