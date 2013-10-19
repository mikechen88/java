package foolstudio.demo.net;

import foolstudio.demo.net.client.ClientDatagramDemoAct;
import foolstudio.demo.net.server.ServerThread;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DatagramDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtLog = null;
	private Button mBtnService = null;
	
	private Handler mHandler = null;
	private ServerThread mServerThread = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_view);
        
        mTxtLog = (EditText)findViewById(R.id.TXT_LOG);
        mBtnService = (Button)findViewById(R.id.BTN_SERVICE);
        Button btnClient = (Button)findViewById(R.id.BTN_CLIENT);
        
        mBtnService.setOnClickListener(this);
        btnClient.setOnClickListener(this);
        
        //初始化界面线程消息处理器
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				Bundle bundle = msg.getData();			
				String destStr = bundle.getCharSequence("DEST").toString();
				String msgStr = bundle.getString("MSG");
				
				printLog(destStr+"|"+msgStr);
			}
		}; 
    }
	
	public void printLog(String msg) {
		mTxtLog.append(msg+"\n");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SERVICE: {
				doService();
				break;
			}
			case R.id.BTN_CLIENT: {
				doClient();
				break;
			}			
		}		
	}

	private void doClient() {
		// TODO Auto-generated method stub
		Intent startClient = new Intent(this, ClientDatagramDemoAct.class);
		this.startActivity(startClient);		
	}

	private void doService() {
		// TODO Auto-generated method stub
		mServerThread = new ServerThread(mHandler);
		mServerThread.start();
		
		mBtnService.setEnabled(false);
	}
};
