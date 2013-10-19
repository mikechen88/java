package foolstudio.demo.sys;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class FoolSysUtil {
	//--------------------------------------------------------------------------
	//获取日期时间字符串
	public static String getDateTimeString() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
											Locale.CHINA);
		StringBuffer buffer = new StringBuffer();

		buffer.append(cal.get(Calendar.YEAR) + "/" +
			(cal.get(Calendar.MONTH) + 1) + "/" +
			cal.get(Calendar.DAY_OF_MONTH) + " " +
			cal.get(Calendar.HOUR_OF_DAY) + ":" +
			cal.get(Calendar.MINUTE) + ":" +
			cal.get(Calendar.SECOND) );

		return (buffer.toString() );
	}
	
	//--------------------------------------------------------------------------	
	//获取时间戳字符串
	public static String getTimestamp() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
											Locale.CHINA);
		StringBuffer buffer = new StringBuffer();

		buffer.append(cal.getTimeInMillis());

		return (buffer.toString() );
	}	
};
