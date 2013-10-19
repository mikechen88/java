package foolstudio.demo.ms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.VideoColumns;

public class VideoStoreUtil {
	
	private static final String[] sColumns = new String[] {
			VideoColumns._ID,
			VideoColumns.TITLE,
			VideoColumns.DISPLAY_NAME,
			VideoColumns.MIME_TYPE,
			//
			VideoColumns.ALBUM,
			VideoColumns.ARTIST,
			VideoColumns.DURATION
         };	
	
	public static void doScan(Context context) {
		Intent startCapture = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		context.startActivity(startCapture);
	}	
	
	public static String getVideoMedia(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = contentResolver.query(
				MediaStore.Video.Media.INTERNAL_CONTENT_URI, sColumns, null, null, null);
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
	
	public static String getVideoMedia2(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		Cursor cursor = contentResolver.query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, sColumns, null, null, null);
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
