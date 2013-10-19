package foolstudio.demo.media;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.AudioColumns;
import android.provider.MediaStore.Video.VideoColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MediaScannerDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnScan = null;
	private Button mBtnGetResult = null;
	private EditText mTxtContents = null;
	
	private static final String SD_DIR = "/sdcard";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnScan = (Button)findViewById(R.id.BTN_SCAN);
        mBtnGetResult = (Button)findViewById(R.id.BTN_GET_RESULT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnScan.setOnClickListener(this);
        mBtnGetResult.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SCAN: {
				doScan();
				break;
			}	
			case R.id.BTN_GET_RESULT: {
				clearText();
				doGetResult();
				break;
			}		
		}
	}
	
	//开始扫描
	private void doScan() {
		// TODO Auto-generated method stub
		Intent startScanner = new Intent(MediaStore.INTENT_ACTION_MEDIA_SEARCH);
		startScanner.putExtra(MediaStore.MEDIA_SCANNER_VOLUME, SD_DIR);
		startActivity(startScanner);
	}
	
	private void doGetResult() {
		// TODO Auto-generated method stub
		doGetAudioResult();
		doGetVideoResult();
	}	
	
	//获取音频媒体扫描结果
	private void doGetAudioResult() {
		// TODO Auto-generated method stub
		Cursor cursor = this.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
				new String[] { AudioColumns.DISPLAY_NAME } , null, null, null);
		//初始化记录游标
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			printText(SD_DIR + "/" + cursor.getString(0) );

			//下一条记录
			cursor.moveToNext();
		}
		
		//关闭游标
		cursor.close();
	}
	
	//获取视频媒体扫描结果
	private void doGetVideoResult() {
		// TODO Auto-generated method stub
		Cursor cursor = this.getContentResolver().query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 
				new String[] { VideoColumns.DISPLAY_NAME } , null, null, null);
		//初始化记录游标
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			printText(SD_DIR + "/" + cursor.getString(0) );

			//下一条记录
			cursor.moveToNext();
		}
		
		//关闭游标
		cursor.close();
	}	

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};