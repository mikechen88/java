package foolstudio.demo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

//�Զ���������ͻ���
public class FoolWebViewerClient extends WebViewClient {
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return (true);
	}
};
