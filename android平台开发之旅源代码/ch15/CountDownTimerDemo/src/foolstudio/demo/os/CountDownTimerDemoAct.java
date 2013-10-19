package foolstudio.demo.os;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CountDownTimerDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	//
	private CountDownTimer mTimer = null;
	
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

	//ȡ����ʱ
	private void doUninit() {
		// TODO Auto-generated method stub
		mTimer.cancel();
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	//��ʼ������ʱ��ʱ��
	private void doInit() {
		// TODO Auto-generated method stub
		
		mTimer = new CountDownTimer(10*1000L, 1*1000L) {

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				printText("ʱ�䵽��");
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				printText("��ʣ"+(millisUntilFinished/1000L)+"��");
			}
		};		

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	//��ʼ����ʱ
	private void doAction() {
		// TODO Auto-generated method stub
		mTimer.start();
		printText("��ʼ����ʱ��");
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