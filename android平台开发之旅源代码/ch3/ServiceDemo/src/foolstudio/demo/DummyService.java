package foolstudio.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class DummyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Log.d(getClass().getName(), "Service created.");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		Toast.makeText(this, "I'm a service!", Toast.LENGTH_LONG).show();
		
		Log.d(getClass().getName(), "Service starting...");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.d(getClass().getName(), "Service destroyed.");
	}
};
