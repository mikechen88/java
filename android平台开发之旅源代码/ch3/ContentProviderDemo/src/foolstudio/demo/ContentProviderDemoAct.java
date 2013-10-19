package foolstudio.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContentProviderDemoAct extends Activity implements OnClickListener{
	
	private ContentResolver mCR = null;
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        Button btnQuery = (Button)findViewById(R.id.BTN_QUERY);
        btnQuery.setOnClickListener(this);
        
        mCR = this.getContentResolver();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_QUERY: {
				clearText();
				doQuery();
				break;
			}
		}
	}
	
	//执行查询
	private void doQuery() {
		//生成资源全路径
		Uri recUri = ContentUris.withAppendedId(MyDB.CONTENT_URI, 
												MyDB.ALL_ROWS);
		//不能进行类型转换，否则会抛出类转换异常
		Cursor mc = mCR.query(recUri, null, null, null, null);
		
		if(mc == null) {
			Toast.makeText(this, "获取游标失败！", Toast.LENGTH_LONG).show();
			return;
		}
			
		mc.moveToFirst(); //复位
		
		while(!mc.isAfterLast()) {
			printText("_id: " + mc.getInt(0) );
			printText("name: " + mc.getString(1) );
			printText("sex: " + mc.getString(2) );
			
			mc.moveToNext();
		}
	
		mc.close();
	}
	
	private void clearText() {
		mTxtContents.setText("");
	}
	
	//打印文本
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
};