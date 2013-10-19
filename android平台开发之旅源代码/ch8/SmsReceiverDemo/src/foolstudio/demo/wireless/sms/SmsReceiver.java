package foolstudio.demo.wireless.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
	
	public static final String ACTION_NAME = 
		"android.provider.Telephony.SMS_RECEIVED";
	public static final String EXTRAS_NAME = "pdus";
	public static final String PARAM_NAME1 = "DEST";
	public static final String PARAM_NAME2 = "MSG";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equalsIgnoreCase(ACTION_NAME) == false) {
			return;
		}
		
		Bundle bundle = intent.getExtras();
		
		if (bundle == null) {
			return;
		}
		
		Object[] pdus = (Object[]) bundle.get(EXTRAS_NAME);
		SmsMessage[] msgs = new SmsMessage[pdus.length];
		for (int i = 0; i < pdus.length; i++) { //遍历消息群
			msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			
			String from = msgs[i].getDisplayOriginatingAddress();
			String text = msgs[i].getDisplayMessageBody();
			
			transmitMsg(context, from, text);
		}
		
		Toast.makeText(context, "Got " + pdus.length + " message(s).", 
					   Toast.LENGTH_LONG).show();
	}

	//传递消息给Activity
	private void transmitMsg(Context context, String from, String text) {
		Intent readMsg = new Intent(context, SmsReaderAct.class);
		readMsg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		readMsg.putExtra(PARAM_NAME1, from);
		readMsg.putExtra(PARAM_NAME2, text);
		context.startActivity(readMsg);	
	}
};
