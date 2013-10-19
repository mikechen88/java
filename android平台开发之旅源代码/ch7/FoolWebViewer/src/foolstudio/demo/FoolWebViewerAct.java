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
        //设置下载侦听器
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setDownloadListener(this);
        //设置上下文菜单
        this.registerForContextMenu(mWebView);
        
        //初始化界面线程消息处理器
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

	//支持返回键（默认的返回键会造成应用程序停止，并返回到系统桌面）
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
			case R.id.MI_BACK: { //回退浏览
				mWebView.goBack();
				break;
			}
			case R.id.MI_FORWARD: { //前进浏览
				mWebView.goForward();
				break;
			}	
			case R.id.MI_HOME: { //到主页
				mWebView.loadUrl(Config.HOME_URL);
				break;
			}
			case R.id.MI_RELOAD: { //刷新
				mWebView.reload();
				break;
			}			
			case R.id.MI_FAVORITE: { //添加收藏夹
				Browser.saveBookmark(this, "NewBookmark", mWebView.getUrl() );
				break;
			}
			case R.id.MI_CAPTURE: { //网页缩略图
				Picture picture = mWebView.capturePicture();
				savePictureToSDCard(picture);
				break;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	//保存图片到SD卡
	private void savePictureToSDCard(Picture picture) {
		// TODO Auto-generated method stub
		try {
			//保存截屏
			OutputStream stream = 
				new FileOutputStream(new File(Config.CAPTURE_PATH) );
			picture.writeToStream(stream);
			stream.flush();
			stream.close();
			//将保存路径通过intent发送给截屏显示Activity
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
		//下载文件
		downloadUrl(url);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId() ) {
			case R.id.MI_DOWNLOAD: {
				HitTestResult htr = this.mWebView.getHitTestResult();
				String url = htr.getExtra();
				
				//下载资源
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
	
	//下载文件
	private void downloadUrl(String url) {
		// TODO Auto-generated method stub
		System.out.println(url);
		
		DownloadThread t = new DownloadThread(url, mHandler);
		t.start();
	}	
};