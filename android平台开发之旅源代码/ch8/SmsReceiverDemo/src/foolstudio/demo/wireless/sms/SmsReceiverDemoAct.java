package foolstudio.demo.wireless.sms;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SmsReceiverDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnStart = null;
	private Button mBtnStop = null;	
	private Button mBtnDetails = null;
	private EditText mTxtContents = null;
	//
	//private Handler mHandler = null;
	//
	private SmsReceiver mReceiver = null;
	private IntentFilter mIntentFilter = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        mBtnStart = (Button)findViewById(R.id.BTN_START);
        mBtnStop = (Button)findViewById(R.id.BTN_STOP);
        mBtnDetails = (Button)findViewById(R.id.BTN_DETAILS); 
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnDetails.setOnClickListener(this);
        
        init();      
    }

    //创建短信消息接收器和意图过滤器
	private void init() {
		// TODO Auto-generated method stub
		mReceiver = new SmsReceiver();
        mIntentFilter = new IntentFilter(SmsReceiver.class.getName() );  		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_START: {
				doStart();
				break;
			}
			case R.id.BTN_STOP: {
				doStop();
				break;
			}
			case R.id.BTN_DETAILS: {
				doDetails();
				break;
			}			
		}
	}

	//查看短信内容
	private void doDetails() {
		// TODO Auto-generated method stub
		Intent detailsIntent = new Intent(this, SmsReaderAct.class);
		detailsIntent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
		this.startActivity(detailsIntent);		
	}

	//注销短信消息接收
	private void doStop() {
		// TODO Auto-generated method stub
		if(mReceiver != null) {
			this.unregisterReceiver(mReceiver);
			
			mBtnStart.setEnabled(true);
			mBtnStop.setEnabled(false);			
		}
	}

	//注册短消息接收器
	private void doStart() {
		// TODO Auto-generated method stub
		this.registerReceiver(mReceiver, mIntentFilter);
		mBtnStart.setEnabled(false);
		mBtnStop.setEnabled(true);
	}
	
	//该方法不能被其他本View之外的线程调用
	private void addMsg(String msg) {
		mTxtContents.append(msg);
		mTxtContents.append("\n");
	}	
};