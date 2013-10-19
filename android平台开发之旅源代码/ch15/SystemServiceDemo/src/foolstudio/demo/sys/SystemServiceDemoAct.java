package foolstudio.demo.sys;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

public class SystemServiceDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtContents = null;
	private Spinner mServiceItems = null;
	private Button mBtnLaunch = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        mServiceItems = (Spinner)findViewById(R.id.SPN_ITEMS);
        mBtnLaunch = (Button)findViewById(R.id.BTN_LAUNCH);
        
        mBtnLaunch.setOnClickListener(this);
        
        mServiceItems.setSelection(15);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_LAUNCH: {
				doLaunch();
				break;
			}		
		}
	}
	
	private void doLaunch() {
		// TODO Auto-generated method stub
		int selectedPos = mServiceItems.getSelectedItemPosition();
		String[] items = getResources().getStringArray(R.array.service_items);
		
		launchService(items[selectedPos]);
	}	

	private void launchService(String text) {
		// TODO Auto-generated method stub
		if(text.equalsIgnoreCase("ACTIVITY_SERVICE") ) {
			ActivityManager service = (ActivityManager)
				(this.getSystemService(Context.ACTIVITY_SERVICE) );
			
			mTxtContents.setText("");
			printText(ActivityServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("ALARM_SERVICE") ) {
			AlarmManager service = (AlarmManager)
				(this.getSystemService(Context.ALARM_SERVICE) );
			
			mTxtContents.setText("");
			printText(AlarmServiceDemo.getInfo(service) );			
		}
		else if(text.equalsIgnoreCase("AUDIO_SERVICE") ) {
			AudioManager service = (AudioManager)
				(this.getSystemService(Context.AUDIO_SERVICE) );	
			
			mTxtContents.setText("");
			printText(AudioServiceDemo.getInfo(service) );			
		}
		else if(text.equalsIgnoreCase("CLIPBOARD_SERVICE") ) {
			ClipboardManager service = (ClipboardManager)
				(this.getSystemService(Context.CLIPBOARD_SERVICE) );
			
			mTxtContents.setText("");
			printText(ClipboardServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("CONNECTIVITY_SERVICE") ) {
			ConnectivityManager service = (ConnectivityManager)
				(this.getSystemService(Context.CONNECTIVITY_SERVICE) );
			
			mTxtContents.setText("");
			printText(ConnectivityServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("INPUT_METHOD_SERVICE") ) {
			InputMethodManager service = (InputMethodManager)
				(this.getSystemService(Context.INPUT_METHOD_SERVICE) );
			
			mTxtContents.setText("");
			printText(InputMethodServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("KEYGUARD_SERVICE") ) {
			KeyguardManager service = (KeyguardManager)
				(this.getSystemService(Context.KEYGUARD_SERVICE) );
			
			mTxtContents.setText("");
			printText(KeyguardServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("LAYOUT_INFLATER_SERVICE") ) {
			LayoutInflater service = (LayoutInflater)
				(this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) );
			
			mTxtContents.setText("");
			printText(LayoutInflaterServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("LOCATION_SERVICE") ) {
			LocationManager service = (LocationManager)
				(this.getSystemService(Context.LOCATION_SERVICE) );
			
			mTxtContents.setText("");
			printText(LocationServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("NOTIFICATION_SERVICE") ) {
			NotificationManager service = (NotificationManager)
				(this.getSystemService(Context.NOTIFICATION_SERVICE) );
			
			mTxtContents.setText("");
			printText(NotificationServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("POWER_SERVICE") ) {
			PowerManager service = (PowerManager)
				(this.getSystemService(Context.POWER_SERVICE) );
			
			mTxtContents.setText("");
			printText(PowerServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("SEARCH_SERVICE") ) {
			SearchManager service = (SearchManager)
				(this.getSystemService(Context.SEARCH_SERVICE) );
			
			mTxtContents.setText("");
			printText(SearchServiceDemo.getInfo(service) );
		}	
		else if(text.equalsIgnoreCase("SENSOR_SERVICE") ) {
			SensorManager service = (SensorManager)
				(this.getSystemService(Context.SENSOR_SERVICE) );
			
			mTxtContents.setText("");
			printText(SensorServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("TELEPHONY_SERVICE") ) {
			TelephonyManager service = (TelephonyManager)
				(this.getSystemService(Context.TELEPHONY_SERVICE) );
			
			mTxtContents.setText("");
			printText(TelephonyServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("VIBRATOR_SERVICE") ) {
			Vibrator service = (Vibrator)
				(this.getSystemService(Context.VIBRATOR_SERVICE) );
			
			mTxtContents.setText("");
			printText(VibratorServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("WALLPAPER_SERVICE") ) {
			/*
			WallpaperService service = (WallpaperService)
				(this.getSystemService(Context.WALLPAPER_SERVICE) );	
			*/		
		}
		else if(text.equalsIgnoreCase("WIFI_SERVICE") ) {
			WifiManager service = (WifiManager)
				(this.getSystemService(Context.WIFI_SERVICE) );
			
			mTxtContents.setText("");
			printText(WifiServiceDemo.getInfo(service) );
		}
		else if(text.equalsIgnoreCase("WINDOW_SERVICE") ) {
			WindowManager service = (WindowManager)
				(this.getSystemService(Context.WINDOW_SERVICE) );
			
			mTxtContents.setText("");
			printText(WindowServiceDemo.getInfo(service) );
		}		
	}

	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}    
}