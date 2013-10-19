package foolstudio.demo.net;

import foolstudio.demo.net.R;
import foolstudio.demo.net.client.ClientSocketDemoAct;
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

public class SocketDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtLog = null;
	private Button mBtnService = null;
	
	private Handler mHandler = null;	
	
	private ServerThread mServerThread = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_view);
        
        mBtnService = (Button)findViewById(R.id.BTN_SERVICE);
        Button btnClient = (Button)findViewById(R.id.BTN_CLIENT);
        mTxtLog = (EditText)findViewById(R.id.TXT_LOG_SERVER);
        
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

	//启动客户端
	private void doClient() {
		// TODO Auto-generated method stub
		Intent startClient = new Intent(this, ClientSocketDemoAct.class);
		this.startActivity(startClient);		
	}

	//启动服务
	private void doService() {
		// TODO Auto-generated method stub		
		mServerThread = new ServerThread(this, mHandler);
		mServerThread.start();
		
		mBtnService.setEnabled(false);
	}
	
    //打印日志
	public void printLog(String msg) {
		mTxtLog.append(msg+"\n");
	}	
};
