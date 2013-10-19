package foolstudio.demo.media;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AudioServiceDemoAct extends Activity 
	implements OnClickListener, OnItemSelectedListener {
	
	private EditText mTxtContents = null;
	private Spinner mItems = null;
	private Spinner mDirections = null;
	private Button mBtnSet = null;
	private SeekBar mBarVolume = null;
	//
	private AudioManager mService = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
    	mItems = (Spinner)findViewById(R.id.SPN_ITEMS);
    	mDirections = (Spinner)findViewById(R.id.SPN_ITEMS2);
    	mBtnSet = (Button)findViewById(R.id.BTN_SET);
    	mBarVolume = (SeekBar)findViewById(R.id.BAR_VOLUME);
    	
    	mBtnSet.setOnClickListener(this);
    	//
    	mItems.setOnItemSelectedListener(this);
    	
    	//获取音频服务管理器
    	mService = (AudioManager)
    		(this.getSystemService(Context.AUDIO_SERVICE) );
    	
    	//初始化
    	mItems.setSelection(0);
    	mDirections.setSelection(0);
    }
    
    //当流类型选择改变时
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		int selectedPos = mItems.getSelectedItemPosition();
		String[] items = getResources().getStringArray(R.array.audio_streams);
		
		final String streamType = items[selectedPos];		
		
		if(streamType.equalsIgnoreCase("STREAM_VOICE_CALL") ) {
			int max = 
				mService.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);			
			mBarVolume.setMax(max);
			mBarVolume.setProgress(max);
		}
		else if(streamType.equalsIgnoreCase("STREAM_SYSTEM") ) {
			int max = 
				mService.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);			
			mBarVolume.setMax(max);			
			mBarVolume.setProgress(max);
		}
		else if(streamType.equalsIgnoreCase("STREAM_RING") ) {
			int max = mService.getStreamMaxVolume(AudioManager.STREAM_RING);			
			mBarVolume.setMax(max);			
			mBarVolume.setProgress(max);
		}
		else if(streamType.equalsIgnoreCase("STREAM_MUSIC") ) {
			int max = mService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);			
			mBarVolume.setMax(max);			
			mBarVolume.setProgress(max);
		}
		else if(streamType.equalsIgnoreCase("STREAM_ALARM") ) {
			int max = mService.getStreamMaxVolume(AudioManager.STREAM_ALARM);			
			mBarVolume.setMax(max);			
			mBarVolume.setProgress(max);
		}		
		
		getSetting(items[selectedPos]);
	}	
	
	//获取设置
	private void getSetting(String streamType) {
		// TODO Auto-generated method stub
		if(streamType.equalsIgnoreCase("STREAM_VOICE_CALL") ) {
			int volume = 
				mService.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_VOICE_CALL volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_SYSTEM") ) {
			int volume = mService.getStreamVolume(AudioManager.STREAM_SYSTEM);
			mBarVolume.setProgress(volume);
			//
			clearText();			
			printText("STREAM_SYSTEM volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_RING") ) {
			int volume = mService.getStreamVolume(AudioManager.STREAM_RING);		
			mBarVolume.setProgress(volume);
			//
			clearText();			
			printText("STREAM_RING volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_MUSIC") ) {
			int volume = mService.getStreamVolume(AudioManager.STREAM_MUSIC);
			mBarVolume.setProgress(volume);
			//
			clearText();			
			printText("STREAM_MUSIC volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_ALARM") ) {
			int volume = mService.getStreamVolume(AudioManager.STREAM_ALARM);	
			mBarVolume.setProgress(volume);
			//
			clearText();			
			printText("STREAM_ALARM volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
	}	

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SET: {
				doSet();
				break;
			}			
		}
	}

	//执行设置
	private void doSet() {
		// TODO Auto-generated method stub
		int selectedPos = mItems.getSelectedItemPosition();
		int selectedPos2 = mDirections.getSelectedItemPosition();
		String[] items = getResources().getStringArray(R.array.audio_streams);
		String[] directions = getResources().getStringArray(R.array.directions);
		
		System.out.println("Item #" + selectedPos + 
						   ", direction #" + selectedPos2);
		
		setSetting(items[selectedPos], directions[selectedPos2]);		
	}
	
	//调整所选流类型的音量大小
	private void setSetting(String streamType, String direction) {
		System.out.println("setSetting"+"("+streamType+","+direction+")");
		// TODO Auto-generated method stub
		int direction2 = 0;
		
		//调整方向
		if(direction.equalsIgnoreCase("ADJUST_LOWER") ) {
			direction2 = AudioManager.ADJUST_LOWER;
			
			System.out.print("Direction is ADJUST_LOWER");
		}
		else if(direction.equalsIgnoreCase("ADJUST_RAISE") ) {
			direction2 = AudioManager.ADJUST_RAISE;
			
			System.out.print("Direction is ADJUST_RAISE");
		}
		else if(direction.equalsIgnoreCase("ADJUST_SAME") ) {
			direction2 = AudioManager.ADJUST_SAME;
			
			System.out.print("Direction is ADJUST_SAME");
		}

		if(streamType.equalsIgnoreCase("STREAM_VOICE_CALL") ) {
			mService.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL, 
										direction2, 
								AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
			int volume = 
				mService.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_VOICE_CALL volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_SYSTEM") ) {
			mService.adjustStreamVolume(AudioManager.STREAM_SYSTEM, 
										direction2, 
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
			int volume = mService.getStreamVolume(AudioManager.STREAM_SYSTEM);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_SYSTEM volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_RING") ) {
			mService.adjustStreamVolume(AudioManager.STREAM_RING, 
										direction2, 
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
			int volume = mService.getStreamVolume(AudioManager.STREAM_RING);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_RING volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_MUSIC") ) {
			mService.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
										direction2, 
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
			int volume = mService.getStreamVolume(AudioManager.STREAM_MUSIC);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_MUSIC volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}
		else if(streamType.equalsIgnoreCase("STREAM_ALARM") ) {
			mService.adjustStreamVolume(AudioManager.STREAM_ALARM, 
										direction2, 
					AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
			int volume = mService.getStreamVolume(AudioManager.STREAM_ALARM);
			mBarVolume.setProgress(volume);
			//
			clearText();
			printText("STREAM_ALARM volume: " + volume + 
					  "/" + mBarVolume.getMax() );
		}		
	}
	
	private void clearText() {
		mTxtContents.setText("");		
	}

	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
};