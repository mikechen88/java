package foolstudio.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Chronometer.OnChronometerTickListener;

public class ChronometerDemoAct extends Activity implements OnClickListener, 
													OnChronometerTickListener {
	private static final long LIMITED_COST = 60*1000L;
	
	private Button mBtnInit = null;
	private Button mBtnUninit = null;
	//
	private Chronometer mChronometer = null;
	//
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (Button)findViewById(R.id.BTN_INIT);
        mBtnUninit = (Button)findViewById(R.id.BTN_UNINIT);
        //        
        mChronometer = (Chronometer)findViewById(R.id.chronometer);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        mBtnInit.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);
        
        mChronometer.setOnChronometerTickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
				doInit();
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
		mChronometer.stop();
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	//初始化设置计时计
	private void doInit() {
		// TODO Auto-generated method stub
		mChronometer.setBase(SystemClock.elapsedRealtime() );
		mChronometer.start();
		
		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}
	
	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
		// TODO Auto-generated method stub
		if(SystemClock.elapsedRealtime()-chronometer.getBase() >= LIMITED_COST) {
			doUninit();
			printText("Time over!");
		}
	}
	
	//--------------------------------------------------------------------------
};