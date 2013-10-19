package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Settings;

public class SystemSettingUtil {
	public static String getSystemSetting(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {
				Settings.System._ID,
				Settings.System.NAME,		
				Settings.System.VALUE
             };
		//��ϵͳ�������ݱ�
		Cursor cursor = contentResolver.query(Settings.System.CONTENT_URI, columns, 
											  null, null, null);
		//��ʼ��
		cursor.moveToFirst();
		
		StringBuffer sb = new StringBuffer();		
		
		while(!cursor.isAfterLast() ) {
			for(int i = 1; i < columns.length; ++i) {
				sb.append(columns[i]);
				sb.append('=');							
				sb.append(cursor.getString(i) );
				
				if(i < (columns.length-1) ) {
					sb.append(',');
				}
			}
			
			sb.append('\n');

			//��һ����¼
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return (sb.toString() );		
	}	
};
