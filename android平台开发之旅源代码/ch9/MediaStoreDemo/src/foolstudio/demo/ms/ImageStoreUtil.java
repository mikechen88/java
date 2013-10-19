package foolstudio.demo.ms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;

public class ImageStoreUtil {
	
	private static final String[] sColumns = new String[] {
		ImageColumns._ID,
		ImageColumns.TITLE,
		ImageColumns.DISPLAY_NAME,
		ImageColumns.MIME_TYPE,
		ImageColumns.MINI_THUMB_MAGIC
	};	
	
	public static void doScan(Context context) {
		Intent startCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		context.startActivity(startCapture);
	}	
	
	public static String getImageMedia(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		Cursor cursor = contentResolver.query(
				MediaStore.Images.Media.INTERNAL_CONTENT_URI, sColumns, null, null, null);
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
	
	public static String getImageMedia2(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = contentResolver.query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, sColumns, null, null, null);
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
