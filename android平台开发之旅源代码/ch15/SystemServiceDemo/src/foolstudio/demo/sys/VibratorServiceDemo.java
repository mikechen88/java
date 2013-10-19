package foolstudio.demo.sys;

import android.os.Vibrator;

public class VibratorServiceDemo {

	public static String getInfo(Vibrator service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		service.vibrate(3000L);
		
		return (sb.toString() );
	}
};
