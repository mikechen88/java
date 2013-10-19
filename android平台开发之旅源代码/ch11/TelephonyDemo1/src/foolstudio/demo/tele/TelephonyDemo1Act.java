package foolstudio.demo.tele;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.EditText;

public class TelephonyDemo1Act extends Activity {
	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        init();        
    }
    
    //��ʼ���绰����
	private void init() {
		// TODO Auto-generated method stub
		TelephonyManager man = 
			(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		man.listen(new FoolPhoneStateListener(), 
				   PhoneStateListener.LISTEN_CALL_STATE);	
	}

	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}  
	
	//�绰״̬����
	class FoolPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			
			printText("Imcoming Number: " + incomingNumber);			
			
			switch(state) {
				case TelephonyManager.CALL_STATE_IDLE: { //����
					printText("IDLE");
					break;
				}
				case TelephonyManager.CALL_STATE_OFFHOOK: { //ժ��
					printText("Off-hook.");
					break;
				}
				case TelephonyManager.CALL_STATE_RINGING : { //����
					printText("Ringing...");
					break;
				}
			}		
		}
	};
	
	//--------------------------------------------------------------------------
};