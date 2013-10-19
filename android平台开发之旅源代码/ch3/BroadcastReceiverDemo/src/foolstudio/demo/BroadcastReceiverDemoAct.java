package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastReceiverDemoAct extends Activity implements OnClickListener {
	
	public static final String INTENT_EXTRAS_NAME = "BroadcastReciverDemoAct";
	
	private DummyBroadcastReceiver mReceiver = null;
	private IntentFilter mIntentFilter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnRegister = (Button)findViewById(R.id.BTN_REGISTER);
        Button btnSend = (Button)findViewById(R.id.BTN_SEND);
        btnRegister.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        
        mReceiver = new DummyBroadcastReceiver();
        mIntentFilter = new IntentFilter(DummyBroadcastReceiver.class.getName() );
    }
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(mReceiver != null) { //注销接收器
			this.unregisterReceiver(mReceiver);
		}
		
		super.onDestroy();
	}    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_REGISTER: {
				doRegister();
				break;
			}
			case R.id.BTN_SEND: {
				doSend();
				break;
			}			
		}
	}
	
	//注册接收器
	private void doRegister() {
		this.registerReceiver(mReceiver, mIntentFilter);
	}
	
	//发送消息
	private void doSend() {
		//Dummy
		Intent sendIntent = new Intent(DummyBroadcastReceiver.class.getName() );
		sendIntent.putExtra(INTENT_EXTRAS_NAME, INTENT_EXTRAS_NAME);
		this.sendBroadcast(sendIntent);
	}
};