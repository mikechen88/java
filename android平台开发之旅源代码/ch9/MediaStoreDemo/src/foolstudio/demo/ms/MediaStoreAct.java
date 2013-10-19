package foolstudio.demo.ms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MediaStoreAct extends Activity implements OnClickListener {
	
	private EditText mTxtContents = null;	
	private Button mBtnScanner = null;
	private Button mBtnAudio = null;
	private Button mBtnPlaylist = null;
	private Button mBtnVideo = null;
	private Button mBtnImage = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        //
        mBtnScanner = (Button)findViewById(R.id.BTN_SCANNER);
        mBtnAudio = (Button)findViewById(R.id.BTN_AUDIO);
        mBtnPlaylist = (Button)findViewById(R.id.BTN_PLAYLIST);
        mBtnVideo = (Button)findViewById(R.id.BTN_VIDEO);
        mBtnImage = (Button)findViewById(R.id.BTN_IMAGE);
        
        mBtnScanner.setOnClickListener(this);
        mBtnAudio.setOnClickListener(this);
        mBtnPlaylist.setOnClickListener(this);
        mBtnVideo.setOnClickListener(this);
        mBtnImage.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SCANNER: {
				mTxtContents.setText(null);
				showScannerSetting();
				break;
			}
			case R.id.BTN_AUDIO: {
				mTxtContents.setText(null);
				showAudioStore();
				break;
			}	
			case R.id.BTN_PLAYLIST: {
				mTxtContents.setText(null);
				showPlaylist();
				break;
			}
			case R.id.BTN_VIDEO: {
				mTxtContents.setText(null);
				showVideoStore();
				break;
			}
			case R.id.BTN_IMAGE: {
				mTxtContents.setText(null);
				showImageStore();
				break;
			}			
		}
	}    

    private void showImageStore() {
		// TODO Auto-generated method stub
		printText(ImageStoreUtil.getImageMedia(getContentResolver()) );
		printText(ImageStoreUtil.getImageMedia2(getContentResolver()) );
		
		ImageStoreUtil.doScan(this);
	}

	private void showVideoStore() {
		// TODO Auto-generated method stub
		printText(VideoStoreUtil.getVideoMedia(getContentResolver()) );
		printText(VideoStoreUtil.getVideoMedia2(getContentResolver()) );	
		
		VideoStoreUtil.doScan(this);
	}

	private void showPlaylist() {
		// TODO Auto-generated method stub
		//printText(PlaylistStoreUtil.getPlaylist(getContentResolver()) );
		printText(PlaylistStoreUtil.getPlaylist2(getContentResolver()) );
		printText(PlaylistStoreUtil.getPlaylistItems2(getContentResolver()) );	
	}

	private void showAudioStore() {
		// TODO Auto-generated method stub
		printText(AudioStoreUtil.getAudioMedia(getContentResolver()) );
		printText(AudioStoreUtil.getAudioMedia2(getContentResolver()) );		
	}

	private void showScannerSetting() {
		// TODO Auto-generated method stub	
		//printText(MediaScannerUtil.getScannerResult(getContentResolver()) );	
		MediaScannerUtil.doScan(this);
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};