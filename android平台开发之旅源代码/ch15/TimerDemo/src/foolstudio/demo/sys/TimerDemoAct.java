package foolstudio.demo.sys;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TimerDemoAct extends Activity implements OnClickListener {
	
	private static final int MSG_ID = 1;
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	
	private Handler mMsgHandler = null;
	private Timer mTimer = null;
	private TimerTask mTask = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (Button)findViewById(R.id.BTN_INIT);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUninit = (Button)findViewById(R.id.BTN_UNINIT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        //
        mBtnInit.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);
        //        
        mMsgHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == MSG_ID) {
					printText(FoolSysUtil.getDateTimeString() );
				}
				super.handleMessage(msg);
			}
        };
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				doInit();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doAction();
				break;
			}			
			case R.id.BTN_UNINIT: {
				doUninit();
				break;
			}		
		}
	}

	private void doUninit() {
		// TODO Auto-generated method stub
		mTimer.cancel();
		mTimer.purge();
		mTimer = null;
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doInit() {
		// TODO Auto-generated method stub
        mTimer = new Timer();	
		mTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mMsgHandler.sendEmptyMessage(MSG_ID);
			}
		};        

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	private void doAction() {
		// TODO Auto-generated method stub
		mTimer.scheduleAtFixedRate(mTask, 0, 1000L);
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	

	//--------------------------------------------------------------------------
};