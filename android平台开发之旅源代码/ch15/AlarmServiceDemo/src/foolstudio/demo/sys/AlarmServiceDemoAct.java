package foolstudio.demo.sys;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

public class AlarmServiceDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnDiscard = null;
	private Button mBtnSetOnce = null;
	private Button mBtnSetRepeat = null;
	private EditText mTxtContents = null;
	
	private AlarmManager mManager = null;
	private PendingIntent mPendingIntent = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        mBtnDiscard = (Button)findViewById(R.id.BTN_CANCEL);
        mBtnSetOnce = (Button)findViewById(R.id.BTN_SET_ONCE);      
        mBtnSetRepeat = (Button)findViewById(R.id.BTN_SET_REPEAT);
        
        mBtnDiscard.setOnClickListener(this);
        mBtnSetOnce.setOnClickListener(this);
        mBtnSetRepeat.setOnClickListener(this);        
        
        mManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmListener.class); 
		mPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		
		mManager.setTimeZone("GMT+08:00");
		//
		mBtnDiscard.setEnabled(false);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_CANCEL: {
				doCancel();
				break;
			}
			case R.id.BTN_SET_ONCE: {
				doSetOnce();
				break;
			}	
			case R.id.BTN_SET_REPEAT: {
				doSetRepeat();
				break;
			}			
		}
	}    
    
	private void doCancel() {
		// TODO Auto-generated method stub
		mManager.cancel(mPendingIntent);
		//
		mBtnDiscard.setEnabled(false);
		mBtnSetOnce.setEnabled(true);
		mBtnSetRepeat.setEnabled(true);
	}

	private void doSetOnce() {
		// TODO Auto-generated method stub
		mManager.set(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + (5*1000), //触发事件（5秒之后）
				mPendingIntent);
		mBtnSetOnce.setEnabled(false);
		mBtnDiscard.setEnabled(true);
	}
	
	private void doSetRepeat() {
		// TODO Auto-generated method stub
		mManager.setRepeating(AlarmManager.RTC_WAKEUP, 
				System.currentTimeMillis() + (5*1000), 
				(5*1000), //间隔
				mPendingIntent);
		mBtnSetRepeat.setEnabled(false);
		mBtnDiscard.setEnabled(true);
	}

	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};