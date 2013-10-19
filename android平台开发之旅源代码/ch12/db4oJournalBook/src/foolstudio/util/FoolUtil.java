package foolstudio.util;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
//
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public final class FoolUtil {
    //--------------------------------------------------------------------------
	//输出日志
	public static void printLog(Context act, final String msg) {
		Log.d(act.getClass().getName(), msg);		
	}	
	
    //--------------------------------------------------------------------------
	//提示消息
	public static void showMsg(Context act, final String msg) {
    	Toast toast = Toast.makeText(act, msg, Toast.LENGTH_LONG);
    	toast.show();		
	}
	
	//-------------------------------------------------------------------------	
	public static String getDateTimeString() {
		Calendar cal = 
			Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), Locale.CHINA);
		StringBuffer buffer = new StringBuffer();

		buffer.append(cal.get(Calendar.YEAR) + "/" +
			(cal.get(Calendar.MONTH) + 1) + "/" +
			cal.get(Calendar.DAY_OF_MONTH) + " " +
			cal.get(Calendar.HOUR_OF_DAY) + ":" +
			cal.get(Calendar.MINUTE) + ":" +
			cal.get(Calendar.SECOND) );

		return (buffer.toString() );
	}	
};
