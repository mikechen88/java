package foolstudio.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Browser;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.Toast;

public class FoolWebViewerAct extends Activity implements DownloadListener {
	//
	private WebView mWebView = null;
	private Handler mHandler = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        mWebView = (WebView)findViewById(R.id.ID_WEB_VIEW);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(Config.HOME_URL);
        mWebView.setWebViewClient(new FoolWebViewerClient() );
        //��������������
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setDownloadListener(this);
        //���������Ĳ˵�
        this.registerForContextMenu(mWebView);
        
        //��ʼ�������߳���Ϣ������
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				Bundle bundle = msg.getData();			
				//String destStr = bundle.getCharSequence("Sender").toString();
				String msgStr = bundle.getString("Msg");
				
				doNotify(msgStr);
			}
		};        
    }
    
    private void doNotify(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	//֧�ַ��ؼ���Ĭ�ϵķ��ؼ������Ӧ�ó���ֹͣ�������ص�ϵͳ���棩
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if( (keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack() ) {
			mWebView.goBack();
			return (true);
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.getMenuInflater().inflate(R.menu.opt_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId() ) {
			case R.id.MI_BACK: { //�������
				mWebView.goBack();
				break;
			}
			case R.id.MI_FORWARD: { //ǰ�����
				mWebView.goForward();
				break;
			}	
			case R.id.MI_HOME: { //����ҳ
				mWebView.loadUrl(Config.HOME_URL);
				break;
			}
			case R.id.MI_RELOAD: { //ˢ��
				mWebView.reload();
				break;
			}			
			case R.id.MI_FAVORITE: { //����ղؼ�
				Browser.saveBookmark(this, "NewBookmark", mWebView.getUrl() );
				break;
			}
			case R.id.MI_CAPTURE: { //��ҳ����ͼ
				Picture picture = mWebView.capturePicture();
				savePictureToSDCard(picture);
				break;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	//����ͼƬ��SD��
	private void savePictureToSDCard(Picture picture) {
		// TODO Auto-generated method stub
		try {
			//�������
			OutputStream stream = 
				new FileOutputStream(new File(Config.CAPTURE_PATH) );
			picture.writeToStream(stream);
			stream.flush();
			stream.close();
			//������·��ͨ��intent���͸�������ʾActivity
			Intent intent = new Intent(this, CaptureViewerAct.class);
			intent.putExtra("FILE_PATH", Config.CAPTURE_PATH);
			this.startActivity(intent);
			
			//Toast.makeText(this, "Capture OK!", Toast.LENGTH_SHORT).show();
		} catch(IOException e) {
			e.printStackTrace();
			
			Toast.makeText(this, "Capture NG!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDownloadStart(String url, String userAgent,
			String contentDisposition, String mimetype, long contentLength) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Want to download " + url, Toast.LENGTH_SHORT).show();
		//�����ļ�
		downloadUrl(url);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId() ) {
			case R.id.MI_DOWNLOAD: {
				HitTestResult htr = this.mWebView.getHitTestResult();
				String url = htr.getExtra();
				
				//������Դ
				if(url != null) {
					downloadUrl(url);
				}
				
				break;
			}
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		this.getMenuInflater().inflate(R.menu.context_menu, menu);
		
		menu.setHeaderIcon(R.drawable.download);
		
		HitTestResult htr = this.mWebView.getHitTestResult();
		String url = htr.getExtra();
		
		if(url != null) {
			menu.setHeaderTitle("Download " + url);
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);		
	}		
	
	//�����ļ�
	private void downloadUrl(String url) {
		// TODO Auto-generated method stub
		System.out.println(url);
		
		DownloadThread t = new DownloadThread(url, mHandler);
		t.start();
	}	
};