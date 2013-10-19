package foolstudio.demo;

import java.io.IOException;
import java.util.ArrayList;

import android.app.TabActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;

public class MusicBoxAct extends TabActivity implements OnItemClickListener {	
	private TabHost mTabHost = null;
	private VideoView mVideoView = null;
	private WebView mWebView = null;
	private MusicBoxWebViewClient mWebViewClient = null;
	private Button mCtrlView = null;
	//
    private Handler mHandler = null;	
	//
	private ListView mListView = null;
	private ArrayList<String> mItems = null;
	private MediaPlayer mMusicPlayer = null;
	//
	private MediaController mController = null;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//填充布局资源为内容视图
        setContentView(R.layout.main);

        //初始化资源文件中所定义的组件
        mTabHost = getTabHost();
        mVideoView = (VideoView)findViewById(R.id.videoView);
        mListView = (ListView)findViewById(R.id.playListView);
        mWebView = (WebView)findViewById(R.id.webView);
        
        //初始化TabHost
        mTabHost.addTab(mTabHost.newTabSpec("tab1")
        		.setIndicator("当前播放").setContent(R.id.videoView));
        mTabHost.addTab(mTabHost.newTabSpec("tab2")
        		.setIndicator("播放列表").setContent(R.id.playListView));
        mTabHost.addTab(mTabHost.newTabSpec("tab3")
        		.setIndicator("推荐资源").setContent(R.id.webView));        
        mTabHost.addTab(mTabHost.newTabSpec("tab4")
        		.setIndicator("本地媒体").setContent(R.id.localView));
        //
        mTabHost.setCurrentTab(2);        
        
        //初始化各个tab
        initList();
        initPlayer();
        initWeb();
		initVideoView();
        
        //播放控件绑定
        mController = new MediaController(this);
        mCtrlView = (Button)findViewById(R.id.controller);
        mController.setAnchorView(mCtrlView);
        mController.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(mController);
        
        //初始化主界面线程消息队列处理器
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
    
    //消息通知
    private void doNotify(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
    
    //初始化播放列表
    private void initList() {
    	mItems = new ArrayList<String>();
    	//使用数据容器构造列表视图适配器
    	ListAdapter adapter = new ArrayAdapter<String>(this, 
    				android.R.layout.simple_list_item_1, mItems);
    	mListView.setAdapter(adapter);
    	//设置列表视图点击时间侦听器
    	mListView.setOnItemClickListener(this);
    }
    
    //初始化播放器
    private void initPlayer() {
    	mMusicPlayer = new MediaPlayer();
    }    
    
    //初始化网页视图和网页视图客户端组件
    private void initWeb() {    	
    	mWebViewClient = new MusicBoxWebViewClient(this);    	
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mWebViewClient);
        //设置上下文菜单
        this.registerForContextMenu(mWebView);        
        //载入网页资源
        mWebView.loadUrl("file:///sdcard/index.html");
    }

    //初始化视频视图
	private void initVideoView() {
		//构造播放控制器
		mController = new MediaController(this);
		mCtrlView = (Button)findViewById(R.id.controller);
		//将播放控制器绑缚可视组件上
		mController.setAnchorView(mCtrlView);
		//设置播放控制器的视频视图
		mController.setMediaPlayer(mVideoView);
		//设置视频视图的控制器
		mVideoView.setMediaController(mController);
	}
    
    //当资源Url改变时（回调函数）
    public void changeUrl(String url) {
    	//重置播放状态
		stopVideo();
		stopAudio();
    	
    	if(url.endsWith(".mp4") || url.endsWith(".3gp") ) { //视频资源
    		playVideo(url);    		
    		mTabHost.setCurrentTab(0); //跳转到播放Tab
    	}
    	else if(url.endsWith(".mp3") || url.endsWith(".wma") ) { //音频资源    		
    		playAudio(url);    		
    		mTabHost.setCurrentTab(1); //跳转到播放列表Tab
    	}	
    }
    
    //播放音频
	private void playAudio(String url) {
    	//Url检查
		String url2 = url.replaceFirst("file://", "");			
		Log.d(getClass().getName(), "Set DataSource: " + url2);
		
		//更新播放列表顺序
		updateList(url2);
    	
		try {
			mMusicPlayer.reset();
			mMusicPlayer.setDataSource(url2);
			mMusicPlayer.prepare();
			mMusicPlayer.start();
		} catch(IOException e) {
			e.printStackTrace();
		}    	
    }

    @SuppressWarnings("unchecked")	
	private void updateList(String url) {
		//更新列表适配器的数据容器
		if(mItems.size() > 0) {
			int index = mItems.indexOf(url);
			
			if(index != -1) { //如果存在重复项
				mItems.remove(index);
			}
			//将新增项放置为首位
			mItems.add(0, url);
		}
		else { //容器内为空时直接添加
			mItems.add(url);
		}
		
		//通知更新数据集
		((ArrayAdapter<String>)mListView.getAdapter()).notifyDataSetChanged();
		
		printList(mItems);
	}
    
    //调试用
    private void printList(ArrayList<String> list) {
    	for(int i = 0; i < list.size(); ++i) {
    		System.out.println("#"+i+": "+list.get(i));
    	}
    }
    
	//停止播放音频
	private void stopAudio() {
		if(mMusicPlayer.isPlaying() ) {
			mMusicPlayer.stop();
		}
	}   
    
	//播放视频
    private void playVideo(String url) {
    	mVideoView.setVideoPath(url); //设置视频路径 
    	mVideoView.start();
    	mVideoView.requestFocus();
    }   
    
    //停止播放视频
    private void stopVideo() {
    	if(mVideoView.isPlaying() ) {
    		mVideoView.stopPlayback();
    	}
    }
    
    //当点击列表视图时回调
	@Override
	public void onItemClick(AdapterView<?> view, View v, int pos, long id) {
		// TODO Auto-generated method stub
		changeUrl(mItems.get(pos) );
    	
    	v.setSelected(true);		
	}
	
	//当选择上下文菜单项时回调
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
			menu.setHeaderTitle("下载【 " + url+"】");
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