package foolstudio.demo.sys;

import android.app.Activity;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FileObserverDemoAct extends Activity implements OnClickListener {
	
	public static final String DEST_PATH="/sdcard/";
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	
	private FoolFileObserver mObserver = null;
	
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
        
        mBtnInit.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);  
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
				doInit();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doAction();
				break;
			}			
			case R.id.BTN_UNINIT: {
				clearText();
				doUninit();
				break;
			}		
		}
	}
	
	//初始化文件系统观察者
	private void doInit() {
		// TODO Auto-generated method stub
		printText("Initializing...");
		
		mObserver = new FoolFileObserver(DEST_PATH, FileObserver.ALL_EVENTS);
		mObserver.setHandler(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Bundle bundle = msg.getData();
				String status = bundle.getString(FoolFileObserver.EXTRAS_KEY);
				
				printText(status);
				
				super.handleMessage(msg);
			}
		});
		mObserver.startWatching();

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}	

	//停止监视行为
	private void doUninit() {
		// TODO Auto-generated method stub
		printText("Uninitializing...");
		mObserver.stopWatching();
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doAction() {
		// TODO Auto-generated method stub
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