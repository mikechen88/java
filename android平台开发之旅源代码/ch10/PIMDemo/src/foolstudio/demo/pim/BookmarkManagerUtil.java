package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Browser;
import android.provider.Browser.BookmarkColumns;

public class BookmarkManagerUtil {
	public static String getBookmarkSetting(ContentResolver contentResolver) {
		String[] columns = new String[] {
				BookmarkColumns._ID,
				BookmarkColumns.BOOKMARK,
				BookmarkColumns.DATE,
				BookmarkColumns.TITLE,
				BookmarkColumns.URL
		};
		//打开浏览器书签信息数据表
		Cursor cursor = contentResolver.query(Browser.BOOKMARKS_URI, columns, 
											  null, null, null);
		//初始化
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
			
			//下一条记录
			cursor.moveToNext();
		}
		
		cursor.close();		
		
		return (sb.toString() );
	}
};
