package foolstudio.demo.sys;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.os.StatFs;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FSUtilDemoAct extends Activity implements OnClickListener {
	
	public static final String DEST_PATH = "/sdcard";
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (Button)findViewById(R.id.BTN_INIT);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUninit = (Button)findViewById(R.id.BTN_UNINIT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnInit.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);        
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				doInit();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doAction();
				break;
			}			
			case R.id.BTN_UNINIT: {
				doUninit();
				break;
			}		
		}
	} 
	
	private void doUninit() {
		// TODO Auto-generated method stub
	}

	private void doInit() {
		// TODO Auto-generated method stub
	}

	private void doAction() {
		// TODO Auto-generated method stub
		StatFs fs = new StatFs(DEST_PATH);
		
		int available = fs.getAvailableBlocks();
		int total = fs.getBlockCount();
		int free = fs.getFreeBlocks();
		double usedRate = ((total-free)*100.0f)/total;
		double freeRate = (free*100.0f)/total;
		
		printText("路径："+DEST_PATH);
		printText("可用块数："+ available); //不包括保留区
		printText("总块数："+ total);
		printText("块大小（Ｂ）："+fs.getBlockSize() );
		printText("空余块数："+free+" ("+ round(freeRate) + "%)"); //包括保留区
		printText("已用块数："+(total-free)+" ("+ round(usedRate) + "%)");
		//printText("可用块数："+fs.restat(ROOT_PATH);		
	}

	private String round(double num) {
		// TODO Auto-generated method stub
		final String parten = "#.##";
		DecimalFormat decimal = new DecimalFormat(parten);
		
		return(decimal.format(num) ); 
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
	
	//--------------------------------------------------------------------------
};