package foolstudio.demo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MusicBoxWebViewClient extends WebViewClient {
	private MusicBoxAct mMainAct = null;
	
	public MusicBoxWebViewClient(MusicBoxAct act) {
		mMainAct = act;
	}
	
	//重载载入媒体资源的URL的方法（统一管理）
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		mMainAct.changeUrl(url);
		//view.loadUrl(url);
		return (true);
	}
};
