package foolstudio.demo.ms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Audio.Playlists;

public class PlaylistStoreUtil {
	private static final String[] sColumns = new String[] {
		Audio.Playlists._ID,
		Audio.PlaylistsColumns.NAME,
		Audio.PlaylistsColumns.DATA,
		Audio.PlaylistsColumns.DATE_ADDED,
		Audio.PlaylistsColumns.DATE_MODIFIED		
	};
	
	//内部存储器的播放列表
	public static String getPlaylist(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = 
			contentResolver.query(MediaStore.Audio.Playlists.INTERNAL_CONTENT_URI, 
								  sColumns, null, null, null);
		//初始化
		cursor.moveToFirst();
		
		StringBuffer sb = new StringBuffer();		
		
		while(!cursor.isAfterLast() ) {
			for(int i = 0; i < sColumns.length; ++i) {
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
	
	//外部存储器的播放列表
	public static String getPlaylist2(ContentResolver contentResolver) {
		// TODO Auto-generated method stub		
		Cursor cursor = 
			contentResolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, 
								  sColumns, null, null, null);
		//初始化
		cursor.moveToFirst();
		
		StringBuffer sb = new StringBuffer();		
		
		while(!cursor.isAfterLast() ) {
			for(int i = 0; i < sColumns.length; ++i) {
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
		
		sb.append(MediaStore.Audio.Playlists.getContentUri("/").toString() );
		sb.append('\n');
		sb.append(MediaStore.Audio.Playlists.Members.getContentUri("/", 1).toString() );		
		
		return (sb.toString() );		
	}
	
	//获取内部存储器的播放项目
	public static String getPlaylistItems2(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {
				Playlists.Members._ID,
				Playlists.Members.AUDIO_ID,
				Playlists.Members.PLAYLIST_ID,
				Playlists.Members.CONTENT_DIRECTORY
             };
		Uri url = MediaStore.Audio.Playlists.Members.getContentUri("/sdcard", 1);
		
		Cursor cursor = contentResolver.query(url, columns, null, null, null);
		
		if(cursor == null) {
			return "";
		}
		
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
