package foolstudio.demo;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class db4oDemoAct extends Activity implements OnClickListener {
	public static final String DATABASE_FILE = "/sdcard/db4oDemoAct.yap";
	private OdbUtil odbUtil = new OdbUtil();
	
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        //获取按钮实例句柄并设置点击事件侦听器
        Button btnAppend = (Button)findViewById(R.id.BTN_APPEND);
        btnAppend.setOnClickListener(this);
        //
        Button btnLookup = (Button)findViewById(R.id.BTN_LOOKUP);
        btnLookup.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_APPEND: {
				clearText();
				doAppend();
				break;
			}
			case R.id.BTN_LOOKUP: {
				clearText();
				doLookup();
				break;
			}			
		}
	}
	
	//存储对象
	private void doAppend() {
        Payout payout = new Payout(odbUtil.getDateTimeString(), 
        						   "Buy drinking water", 
        						   3.0D);
        odbUtil.appendObject(DATABASE_FILE, payout);
        
        printText("Store " + payout.toString() );		
	}
	
	//列举对象
	private void doLookup() {
		Payout proto = new Payout(null, null, 0.0D);
		Vector<Payout> objects = odbUtil.getObjects(DATABASE_FILE, proto);
		
		for(int i = 0; i < objects.size(); ++i) {
			Payout payout = objects.get(i);
			//
			printText(payout.toString() );
		}
	}
	
	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}		
};