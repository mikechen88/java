package foolstudio.demo.view;

import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class SimpleTabHostDemoAct extends TabActivity implements OnClickListener {
	
	private Button mBtnAction = null;
	
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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnAction = (Button)findViewById(R.id.BTN);
        
        //获取TabHost组件对象实例
        TabHost tabHost = getTabHost();
        
        //初始化TabHost的标签页
        tabHost.addTab(tabHost.newTabSpec("tab1")
        		.setIndicator("标签页1").setContent(R.id.TXT) );
        tabHost.addTab(tabHost.newTabSpec("tab2")
        		.setIndicator("标签页2").setContent(R.id.IMG) );
        //设置当前标签页
        tabHost.setCurrentTab(1);
        
        mBtnAction.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		doScan(this);
		System.out.println(getAudioMedia2(this.getContentResolver()) );
	}
	
	public static void doScan(Context context) {
		Intent startScanner = new Intent(MediaStore.INTENT_ACTION_MEDIA_SEARCH);
		startScanner.putExtra(MediaStore.MEDIA_SCANNER_VOLUME, "/sdcard");
		context.startActivity(startScanner);
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