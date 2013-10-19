package foolstudio.demo.media;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;

public class AudioStoreUtil {
	
	private static final String[] sColumns = new String[] {
		AudioColumns._ID,
		AudioColumns.TITLE,
		AudioColumns.DISPLAY_NAME,
		AudioColumns.MIME_TYPE,
		//
		AudioColumns.ALBUM,
		AudioColumns.ARTIST,
		AudioColumns.DURATION
	};	
	
	public static String getAudioMedia(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = contentResolver.query(
				MediaStore.Audio.Media.INTERNAL_CONTENT_URI, sColumns, null, null, null);
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
	
	public static String getAudioMedia2(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = contentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, sColumns, null, null, null);
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
