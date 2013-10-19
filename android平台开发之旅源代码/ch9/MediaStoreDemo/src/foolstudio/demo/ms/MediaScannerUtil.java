package foolstudio.demo.ms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;

public class MediaScannerUtil {
	
	private static final String[] sColumns = new String[] {
		MediaColumns._ID,
		MediaColumns.TITLE,
		MediaColumns.DISPLAY_NAME,		
		MediaColumns.DATA,
		MediaColumns.MIME_TYPE,
		MediaColumns.SIZE
	};	
	
	public static void doScan(Context context) {
		Intent startScanner = new Intent(MediaStore.INTENT_ACTION_MEDIA_SEARCH);
		startScanner.putExtra(MediaStore.MEDIA_SCANNER_VOLUME, "/sdcard");
		context.startActivity(startScanner);
	}
	
	public static String getScannerResult(ContentResolver contentResolver) {		
		Cursor cursor = contentResolver.query(MediaStore.getMediaScannerUri(),
				sColumns, null, null, null);
		//初始化
		cursor.moveToFirst();
		
		StringBuffer sb = new StringBuffer();
		
		while(!cursor.isAfterLast() ) {			
			for(int i = 1; i < sColumns.length; ++i) {
				sb.append(sColumns[i]);
				sb.append('=');				
				sb.append(cursor.getString(i) );
				
				if(i < (sColumns.length-1) ) {
					sb.append(',');
				}
			}

			sb.append('\n');
			
			//下一条记录
			cursor.moveToNext();
		}
		
		cursor.close();		
		
		return (sb.toString() );
	}
};
