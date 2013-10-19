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
		//��䲼����ԴΪ������ͼ
        setContentView(R.layout.main);

        //��ʼ����Դ�ļ�������������
        mTabHost = getTabHost();
        mVideoView = (VideoView)findViewById(R.id.videoView);
        mListView = (ListView)findViewById(R.id.playListView);
        mWebView = (WebView)findViewById(R.id.webView);
        
        //��ʼ��TabHost
        mTabHost.addTab(mTabHost.newTabSpec("tab1")
        		.setIndicator("��ǰ����").setContent(R.id.videoView));
        mTabHost.addTab(mTabHost.newTabSpec("tab2")
        		.setIndicator("�����б�").setContent(R.id.playListView));
        mTabHost.addTab(mTabHost.newTabSpec("tab3")
        		.setIndicator("�Ƽ���Դ").setContent(R.id.webView));        
        mTabHost.addTab(mTabHost.newTabSpec("tab4")
        		.setIndicator("����ý��").setContent(R.id.localView));
        //
        mTabHost.setCurrentTab(2);        
        
        //��ʼ������tab
        initList();
        initPlayer();
        initWeb();
		initVideoView();
        
        //���ſؼ���
        mController = new MediaController(this);
        mCtrlView = (Button)findViewById(R.id.controller);
        mController.setAnchorView(mCtrlView);
        mController.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(mController);
        
        //��ʼ���������߳���Ϣ���д�����
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
    
    //��Ϣ֪ͨ
    private void doNotify(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
    
    //��ʼ�������б�
    private void initList() {
    	mItems = new ArrayList<String>();
    	//ʹ���������������б���ͼ������
    	ListAdapter adapter = new ArrayAdapter<String>(this, 
    				android.R.layout.simple_list_item_1, mItems);
    	mListView.setAdapter(adapter);
    	//�����б���ͼ���ʱ��������
    	mListView.setOnItemClickListener(this);
    }
    
    //��ʼ��������
    private void initPlayer() {
    	mMusicPlayer = new MediaPlayer();
    }    
    
    //��ʼ����ҳ��ͼ����ҳ��ͼ�ͻ������
    private void initWeb() {    	
    	mWebViewClient = new MusicBoxWebViewClient(this);    	
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mWebViewClient);
        //���������Ĳ˵�
        this.registerForContextMenu(mWebView);        
        //������ҳ��Դ
        mWebView.loadUrl("file:///sdcard/index.html");
    }

    //��ʼ����Ƶ��ͼ
	private void initVideoView() {
		//���첥�ſ�����
		mController = new MediaController(this);
		mCtrlView = (Button)findViewById(R.id.controller);
		//�����ſ������󸿿��������
		mController.setAnchorView(mCtrlView);
		//���ò��ſ���������Ƶ��ͼ
		mController.setMediaPlayer(mVideoView);
		//������Ƶ��ͼ�Ŀ�����
		mVideoView.setMediaController(mController);
	}
    
    //����ԴUrl�ı�ʱ���ص�������
    public void changeUrl(String url) {
    	//���ò���״̬
		stopVideo();
		stopAudio();
    	
    	if(url.endsWith(".mp4") || url.endsWith(".3gp") ) { //��Ƶ��Դ
    		playVideo(url);    		
    		mTabHost.setCurrentTab(0); //��ת������Tab
    	}
    	else if(url.endsWith(".mp3") || url.endsWith(".wma") ) { //��Ƶ��Դ    		
    		playAudio(url);    		
    		mTabHost.setCurrentTab(1); //��ת�������б�Tab
    	}	
    }
    
    //������Ƶ
	private void playAudio(String url) {
    	//Url���
		String url2 = url.replaceFirst("file://", "");			
		Log.d(getClass().getName(), "Set DataSource: " + url2);
		
		//���²����б�˳��
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
		//�����б�����������������
		if(mItems.size() > 0) {
			int index = mItems.indexOf(url);
			
			if(index != -1) { //��������ظ���
				mItems.remove(index);
			}
			//�����������Ϊ��λ
			mItems.add(0, url);
		}
		else { //������Ϊ��ʱֱ�����
			mItems.add(url);
		}
		
		//֪ͨ�������ݼ�
		((ArrayAdapter<String>)mListView.getAdapter()).notifyDataSetChanged();
		
		printList(mItems);
	}
    
    //������
    private void printList(ArrayList<String> list) {
    	for(int i = 0; i < list.size(); ++i) {
    		System.out.println("#"+i+": "+list.get(i));
    	}
    }
    
	//ֹͣ������Ƶ
	private void stopAudio() {
		if(mMusicPlayer.isPlaying() ) {
			mMusicPlayer.stop();
		}
	}   
    
	//������Ƶ
    private void playVideo(String url) {
    	mVideoView.setVideoPath(url); //������Ƶ·�� 
    	mVideoView.start();
    	mVideoView.requestFocus();
    }   
    
    //ֹͣ������Ƶ
    private void stopVideo() {
    	if(mVideoView.isPlaying() ) {
    		mVideoView.stopPlayback();
    	}
    }
    
    //������б���ͼʱ�ص�
	@Override
	public void onItemClick(AdapterView<?> view, View v, int pos, long id) {
		// TODO Auto-generated method stub
		changeUrl(mItems.get(pos) );
    	
    	v.setSelected(true);		
	}
	
	//��ѡ�������Ĳ˵���ʱ�ص�
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
			menu.setHeaderTitle("���ء� " + url+"��");
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