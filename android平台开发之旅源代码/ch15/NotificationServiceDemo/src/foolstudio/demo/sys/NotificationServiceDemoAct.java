package foolstudio.demo.sys;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationServiceDemoAct extends Activity implements 
														 OnClickListener {
	
	private Button mBtnRegister = null;
	private Button mBtnDo = null;
	private Button mBtnUnregister = null;	
	
	private NotificationManager mService = null;
	private Notification mNotification = null;
	
	public static final int NOTIFICATION_ID = 1;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnRegister = (Button)findViewById(R.id.BTN_REGISTER);
        mBtnDo = (Button)findViewById(R.id.BTN_NOTIFY);
        mBtnUnregister = (Button)findViewById(R.id.BTN_UNREGISTER);
        
        mBtnRegister.setOnClickListener(this);
        mBtnDo.setOnClickListener(this);
        mBtnUnregister.setOnClickListener(this);        
        
        init();
    }

    //初始化通知服务
	private void init() {
		// TODO Auto-generated method stub
		mService = (NotificationManager)
			getSystemService(Context.NOTIFICATION_SERVICE);
		mNotification = new Notification(R.drawable.tip,
				"Reminder", 
				System.currentTimeMillis() );		
		setStates(false);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_REGISTER: {
				doRegister();
				break;
			}	
			case R.id.BTN_NOTIFY: {
				doNotify();
				break;
			}			
			case R.id.BTN_UNREGISTER: {
				doUnregister();
				break;
			}		
		}
	}
	
	//注册通知侦听
	private void doRegister() {
		// TODO Auto-generated method stub
		Intent notifyIntent = new Intent(this, RemindAct.class);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		PendingIntent contentIntent = 
			PendingIntent.getActivity(this, 0, notifyIntent, 0);
		
		mNotification.setLatestEventInfo(this.getApplicationContext(),
				"New reminder", "It's sleeping time!", contentIntent);
		setStates(true);
	}	

	//设置按钮状态
	private void setStates(boolean isRegistered) {
		// TODO Auto-generated method stub
		mBtnRegister.setEnabled(!isRegistered);
		mBtnUnregister.setEnabled(isRegistered);
		mBtnDo.setEnabled(isRegistered);
	}

	//发出通知
	private void doNotify() {
		// TODO Auto-generated method stub
		mService.notify(NOTIFICATION_ID, mNotification);
	}

	//注销通知侦听
	private void doUnregister() {
		// TODO Auto-generated method stub
		mService.cancel(NOTIFICATION_ID);
		
		setStates(false);
	}
};