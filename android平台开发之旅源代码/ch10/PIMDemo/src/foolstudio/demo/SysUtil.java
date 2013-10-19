package foolstudio.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.database.Cursor;

public class SysUtil {
	//��Unixʱ���ת��������ʱ���ַ���
	public static String unixTimestamp2Str(long epoch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00") );
        Date theDate = new Date(epoch);
        return (sdf.format(theDate) );
	}
	
	//��ȡ�α���
	public static String getResult(Cursor cursor, String[] columns) {
		StringBuffer sb = new StringBuffer();		

		//��ʼ����¼�α�
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			
			for(int i = 0; i < columns.length; ++i) {
				sb.append(columns[i]+'='+cursor.getString(i) );
				
				if(i < (columns.length-1) ) {
					sb.append(',');
				}
			}

			sb.append('\n');
			//��һ����¼
			cursor.moveToNext();
		}
		
		cursor.close();		
		return(sb.toString() );		
	}
};
