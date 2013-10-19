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
	
	//ִ�в�ѯ
	private void doQuery() {
		//������Դȫ·��
		Uri recUri = ContentUris.withAppendedId(MyDB.CONTENT_URI, 
												MyDB.ALL_ROWS);
		//���ܽ�������ת����������׳���ת���쳣
		Cursor mc = mCR.query(recUri, null, null, null, null);
		
		if(mc == null) {
			Toast.makeText(this, "��ȡ�α�ʧ�ܣ�", Toast.LENGTH_LONG).show();
			return;
		}
			
		mc.moveToFirst(); //��λ
		
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
	
	//��ӡ�ı�
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
};