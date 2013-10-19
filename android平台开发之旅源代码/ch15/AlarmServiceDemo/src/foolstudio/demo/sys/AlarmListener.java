package foolstudio.demo.sys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "Ding-ding", Toast.LENGTH_LONG).show(); 
	}
};
