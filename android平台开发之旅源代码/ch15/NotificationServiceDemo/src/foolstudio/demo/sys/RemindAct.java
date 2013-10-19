package foolstudio.demo.sys;

/*
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
*/

import android.app.Activity;
import android.os.Bundle;
/*
import android.os.SystemClock;
import android.widget.Toast;
*/
//import android.widget.AnalogClock;
//import android.widget.DigitalClock;

public class RemindAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.remind_view);
		
		//无法通过API设置系统时间
		/*
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
											Locale.CHINESE);
		if(SystemClock.setCurrentTimeMillis(cal.getTimeInMillis() ) == false) {
			Toast.makeText(this, "Update syatem clock failed!", 
						   Toast.LENGTH_SHORT).show();
		}
		*/
		
		/*
		AnalogClock clocker = (AnalogClock)findViewById(R.id.CLK_MAIN);
		clocker.setSoundEffectsEnabled(true);
		DigitalClock clocker2 = (DigitalClock)findViewById(R.id.CLK_TEXT);
		*/
	}
};
