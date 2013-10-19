package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;

public class SettingUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {
				Contacts.SettingsColumns.KEY,
				Contacts.SettingsColumns.VALUE
             };
		//�򿪰�ȫ�������ݱ�
		Cursor cursor = contentResolver.query(Contacts.Settings.CONTENT_URI, 
											  columns, null, null, null);
		//��ʼ���α�
		cursor.moveToFirst();
		
		StringBuffer sb = new StringBuffer();		
		
		while(!cursor.isAfterLast() ) {
			for(int i = 1; i < columns.length; ++i) {
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
		
		return (sb.toString() );		
	}
};
