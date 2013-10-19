package foolstudio.demo.wireless.sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SmsDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtDest = null;
	private EditText mTxtMsg = null;
	private Button mBtnDiscard = null;
	private Button mBtnSend = null;
	private CheckBox mChxMsg = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtDest = (EditText)findViewById(R.id.TXT_DEST);
        mTxtMsg = (EditText)findViewById(R.id.TXT_MSG);
        mChxMsg = (CheckBox)findViewById(R.id.CHX_MSG);
        mBtnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
        mBtnSend = (Button)findViewById(R.id.BTN_SEND);
        
        mBtnDiscard.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);        
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				doDiscard();
				break;
			}
			case R.id.BTN_SEND: {
				doSend();
				break;
			}			
		}
	}

	private void doSend() {
		// TODO Auto-generated method stub
		String dest = mTxtDest.getText().toString().trim();
		String text = mTxtMsg.getText().toString().trim();
		
		if(dest.length() < 1 || text.length() < 1) {
			Toast.makeText(this, "Destination or message can't empty!", 
						   Toast.LENGTH_LONG).show();
			return;
		}
		
		SmsManager man = SmsManager.getDefault();
		PendingIntent intentSendSms = PendingIntent.getBroadcast(this, 0, 
				new Intent(), PendingIntent.FLAG_ONE_SHOT);
		
		if(mChxMsg.isChecked() ) { //文本消息
			man.sendTextMessage(dest, null, text, intentSendSms, null);
		}
		else { //数据消息
			short port = 1982;
			man.sendDataMessage(dest, null, port, text.getBytes(), 
								intentSendSms, null);
		}
	}

	private void doDiscard() {
		// TODO Auto-generated method stub
		mTxtMsg.setText("");
	}    
};