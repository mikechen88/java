package foolstudio.demo.media;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScannerDemoAct extends Activity implements OnClickListener, MediaScannerConnectionClient {
	
	private Button mBtnAction = null;
	
	private MediaScannerConnection mConnection = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnAction.setOnClickListener(this);
        
        initConnection();
    }
	
    private void initConnection() {
		// TODO Auto-generated method stub
    	mConnection = new MediaScannerConnection(this, this);
    	mConnection.connect();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_ACTION: {
				doAction();
				break;
			}		
		}
	}

	private void doAction() {
		// TODO Auto-generated method stub
		mConnection.scanFile("/sdcard", "video/3gpp");
		
		Intent intent = new Intent();
		intent.setAction(MediaStore.INTENT_ACTION_MEDIA_SEARCH);
		intent.putExtra(MediaStore.MEDIA_SCANNER_VOLUME, "/sdcard");
		startActivity(intent);
	}


	@Override
	public void onMediaScannerConnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScanCompleted(String path, Uri uri) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getSimpleName(), "Path="+path+", uri=" + uri);
		
		//Path=/sdcard/fish2.3gp, uri=content://media/external/video/media/6
		Uri uri2 = Uri.parse("content://media/external/video/media/#");

		
		String[] columns = null;//{ MediaStore.MediaColumns.DISPLAY_NAME};
		
		Cursor c = MediaStore.Video.query(this.getContentResolver(), uri2, columns);
		c.moveToFirst();
		
		int count = c.getColumnCount();
		
		do{
		
			for(int i = 0; i < count; ++i) {
				Log.d("=======================", c.getString(i) );
			}
		}while(c.moveToNext());
		
		
		c.close();
	}
};