package foolstudio.demo.net.ssl;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SslSocketDemoAct extends Activity implements OnClickListener {
	
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
				try {
					doInit();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doInit() throws IOException {
		
		this.printText("Connect to 127.0.0.1:10086 ...");
		this.printText("Request 'Hello, SSL Server!'");
		this.printText("Got message 'Talking over, bye!'");
		
		// TODO Auto-generated method stub
		ClientSocketThread t = new ClientSocketThread(this, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Bundle bundle = msg.getData();
				String status = bundle.getString(ClientSocketThread.EXTRAS_KEY);
				
				printText(status);
				
				super.handleMessage(msg);
			}
		});
		t.start();

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	private void doAction() {
		// TODO Auto-generated method stub
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