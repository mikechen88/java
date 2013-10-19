package foolstudio.demo.sys;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class KeyguardServiceDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtContents = null;	
	private KeyguardManager mService = null;
	private KeyguardManager.KeyguardLock mLocker = null;
	private Button mBtnLock = null;
	private Button mBtnUnlock = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        mBtnLock = (Button)findViewById(R.id.BTN_LOCK);
        mBtnUnlock = (Button)findViewById(R.id.BTN_UNLOCK);
        
        mBtnLock.setOnClickListener(this);
        mBtnUnlock.setOnClickListener(this);           
        //
        mService = (KeyguardManager)
        	(this.getSystemService(Context.KEYGUARD_SERVICE) );
        mLocker = mService.newKeyguardLock(this.getLocalClassName() );
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_LOCK: {
				doLock();
				break;
			}
			case R.id.BTN_UNLOCK: {
				doUnlock();
				break;
			}			
		}
	}    

	//½âËø
	private void doUnlock() {
		// TODO Auto-generated method stub
		mLocker.reenableKeyguard();
		
		mBtnLock.setEnabled(true);
		mBtnUnlock.setEnabled(false);
		
		printText("Unlocked");
	}

	//Ëø¶¨
	private void doLock() {
		// TODO Auto-generated method stub
		mLocker.disableKeyguard();
		
		mBtnLock.setEnabled(false);
		mBtnUnlock.setEnabled(true);		
		
		printText("Locked");
	}

	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mService.exitKeyguardSecurely(new KeyguardExitListener() );
	}
};