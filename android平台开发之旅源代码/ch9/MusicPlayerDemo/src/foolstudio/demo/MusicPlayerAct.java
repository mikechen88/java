package foolstudio.demo;

import java.io.IOException;
//
import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MusicPlayerAct extends ListActivity {
	//播放状态
	private static final int STATUS_STOPED = 0;	
	private static final int STATUS_PLAYING = 1;
	private static final int STATUS_PAUSED = 2;
	
	public static final String[] FILE_PATH_ARRAY = {
		"/sdcard/Lonely.mp3",
		"/sdcard/Crazy.mp3",
		"/sdcard/Sorrow.wma",
		"/sdcard/Romantic.wma",	
		"/sdcard/God.mp3"		
	};
	
	private final String[] ITEMS_ARRAY = {
		"Lonely [MP3]",
		"Crazy [MP3]",
		"Sorrow [WMA]",
		"Romantic [WMA]",	
		"God is a girl [MP3]"		
	};
	
	private MediaPlayer mMusicPlayer = null;
	//
	private int mStatus = STATUS_STOPED;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //绑定数据
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.row, 
        											   R.id.rowItem, 
        											   ITEMS_ARRAY);
        setListAdapter(adapter);
        
        initView();        
    }
    
    private void initView() {
    	mMusicPlayer = new MediaPlayer();
    } 
    
    public void onStart() {
    	super.onStart();
    	
    	getListView().setSelection(1);    	
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
    	doStop();
    	doPlay(pos);
    	
    	v.setSelected(true);
    }    

	//播放行为
	private void doPlay(int position) {
		switch(mStatus) {
			case STATUS_STOPED: {
				if(playMusic(FILE_PATH_ARRAY[position]) ) {
					setStatus(STATUS_PLAYING);
				}				
				break;
			}
			case STATUS_PLAYING: {
				mMusicPlayer.pause();
				setStatus(STATUS_PAUSED);
				break;
			}
			case STATUS_PAUSED: {
				mMusicPlayer.start();
				setStatus(STATUS_PLAYING);
				break;
			}		
		}
	}
	
	//设置暂停
	private void setStatus(int status) {
		
		mStatus = status;
		
		switch(status) {
			case STATUS_STOPED: {
				break;
			}
			case STATUS_PLAYING: {
				break;
			}
			case STATUS_PAUSED: {
				break;
			}
		}
	}
    
	//播放指定路径的音乐
	private boolean playMusic(String path) {
		try {
			mMusicPlayer.reset();
			mMusicPlayer.setDataSource(path);
			mMusicPlayer.prepare();
			mMusicPlayer.start();
		}
		catch(IOException e) {
			e.printStackTrace();
			return(false);
		}
		
		Log.d(getClass().getName(), "Start playing "+path);		
		
		return(true);
	}
	
	//停止播放
	private void doStop() {
		if(mMusicPlayer.isPlaying() ) {
			mMusicPlayer.stop();
			
			setStatus(STATUS_STOPED);
		}
	}
};