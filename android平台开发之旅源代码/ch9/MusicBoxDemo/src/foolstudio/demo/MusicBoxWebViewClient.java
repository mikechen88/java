package foolstudio.demo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MusicBoxWebViewClient extends WebViewClient {
	private MusicBoxAct mMainAct = null;
	
	public MusicBoxWebViewClient(MusicBoxAct act) {
		mMainAct = act;
	}
	
	//��������ý����Դ��URL�ķ�����ͳһ����
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		mMainAct.changeUrl(url);
		//view.loadUrl(url);
		return (true);
	}
};
