package foolstudio.demo.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import foolstudio.demo.net.Config;
import foolstudio.demo.net.R;
//
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ClientSocketDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtMsg = null;
	private Button mBtnSend = null;
	private EditText mTxtLog = null;
	//
	private Socket mClientSocket = null;
	private BufferedReader mStreamReader = null;
	private PrintWriter mStreamWriter = null;
	
	private ClientThread mClientThread = null;
	
	private Handler mHandler = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_view);
        
        mTxtMsg = (EditText)findViewById(R.id.TXT_MSG);
        mBtnSend = (Button)findViewById(R.id.BTN_SEND);
        mTxtLog = (EditText)findViewById(R.id.TXT_LOG_CLIENT);
        
        mBtnSend.setOnClickListener(this);
        
        initSocket();
        
        //主线程消息处理器
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				Bundle bundle = msg.getData();
				String destStr = bundle.getCharSequence("DEST").toString();
				String msgStr = bundle.getString("MSG");
				
				if(msgStr.equalsIgnoreCase(Config.BYE) ) { //接收到结束通知
					doQuit();
					return;
				}
				
				printLog(destStr+"|"+msgStr);
			}
		};        
    }

    //初始化服务套接字
	private void initSocket() {
		// TODO Auto-generated method stub
		try {
			mClientSocket = new Socket(Config.SERVER_HOST, Config.SERVER_PORT);
			mStreamWriter = new PrintWriter(mClientSocket.getOutputStream(), true);
			mStreamReader = new BufferedReader(
					new InputStreamReader(mClientSocket.getInputStream()), 
					Config.BUFFER_SIZE);			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SEND: {
				doSend();
				break;
			}
		}
	}

	//向服务端发送消息
	private void doSend() {
		// TODO Auto-generated method stub		
		String msg = mTxtMsg.getText().toString().trim();

		mClientThread = new ClientThread(this, msg, mHandler);
		mClientThread.start();
	}
	
	//该方法不能被其他本View之外的线程调用
	private void printLog(String msg) {
		mTxtLog.append(msg+"\n");
	}	

	public Socket getClientSocket() {
		return mClientSocket;
	}

	public BufferedReader getStreamReader() {
		return mStreamReader;
	}

	public PrintWriter getStreamWriter() {
		return mStreamWriter;
	}

	//结束本客户端
	private void doQuit() {
		// TODO Auto-generated method stub
		printLog(Config.BYE);
		
		mClientThread.setFinish(true);
		
		try {
			mStreamReader.close();
			mStreamWriter.close();
			mClientSocket.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.finish();
	}	
};
