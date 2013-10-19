package foolstudio.demo.wireless.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SmsReaderAct extends Activity {

	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_view);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        
        if(bundle == null) {
        	return;
        }
        
        addMsg(SmsReceiver.PARAM_NAME1, 
        		bundle.getString(SmsReceiver.PARAM_NAME1) );
        addMsg(SmsReceiver.PARAM_NAME2, 
        		bundle.getString(SmsReceiver.PARAM_NAME2) );
    }
    
	//该方法不能被其他本View之外的线程调用
	public void addMsg(String key, String val) {
		mTxtContents.append(key+": "+val+"\n");
	}    
};
