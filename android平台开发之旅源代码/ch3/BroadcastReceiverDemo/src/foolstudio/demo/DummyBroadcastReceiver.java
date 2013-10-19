package foolstudio.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DummyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg = 
			intent.getStringExtra(BroadcastReceiverDemoAct.INTENT_EXTRAS_NAME);
		
		Toast.makeText(context, "Get message: " + msg, Toast.LENGTH_LONG).show();
	}
};
