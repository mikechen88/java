package foolstudio.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.database.Cursor;

public class SysUtil {
	//将Unix时间戳转换成日期时间字符串
	public static String unixTimestamp2Str(long epoch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00") );
        Date theDate = new Date(epoch);
        return (sdf.format(theDate) );
	}
	
	//获取游标结果
	public static String getResult(Cursor cursor, String[] columns) {
		StringBuffer sb = new StringBuffer();		

		//初始化记录游标
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			
			for(int i = 0; i < columns.length; ++i) {
				sb.append(columns[i]+'='+cursor.getString(i) );
				
				if(i < (columns.length-1) ) {
					sb.append(',');
				}
			}

			sb.append('\n');
			//下一条记录
			cursor.moveToNext();
		}
		
		cursor.close();		
		return(sb.toString() );		
	}
};
