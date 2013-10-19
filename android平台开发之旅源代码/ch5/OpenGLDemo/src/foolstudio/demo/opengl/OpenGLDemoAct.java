package foolstudio.demo.opengl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OpenGLDemoAct extends Activity implements OnClickListener {
	
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
		printText("Uninitializing...");
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doInit() {
		// TODO Auto-generated method stub
		printText("Initializing...");

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	private void doAction() {
		// TODO Auto-generated method stub
		printText("Do action...");
		
		Intent intentNew = new Intent(this, OpenGLDrawAct.class);
		this.startActivity(intentNew);
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