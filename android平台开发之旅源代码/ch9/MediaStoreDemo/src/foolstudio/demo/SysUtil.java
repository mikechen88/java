package foolstudio.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SysUtil {
	//��Unixʱ���ת��������ʱ���ַ���
	public static String unixTimestamp2Str(long epoch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00") );
        Date theDate = new Date(epoch);
        return (sdf.format(theDate) );
	}
};
