package foolstudio.demo.net.client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

public class ClientDatagramDemoAct extends Activity implements OnClickListener {

	private Handler mHandler = null;	
	
	private EditText mTxtMsg = null;
	private EditText mTxtLog = null;
	//
	private DatagramSocket mClientDatagram = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_view);
        
        mTxtMsg = (EditText)findViewById(R.id.TXT_MSG);
        Button btnSend = (Button)findViewById(R.id.BTN_SEND);
        mTxtLog = (EditText)findViewById(R.id.TXT_LOG);
        
        btnSend.setOnClickListener(this);
        
        initDatagram();
        
        //�������߳���Ϣ������
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

    //��ʼ�����ݱ�
	private void initDatagram() {
		// TODO Auto-generated method stub
		try {
			mClientDatagram = new DatagramSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��������
		try {
			mClientDatagram.connect(InetAddress.getByName(Config.SERVER_HOST),
									Config.SERVER_PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
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

	//������Ϣ
	private void doSend() {		
		// TODO Auto-generated method stub
		ClientThread t = new ClientThread(this, mHandler, mTxtMsg.getText().toString() );
		t.start();
	}
	
	//�÷������ܱ�������View֮����̵߳���
	public void printLog(String msg) {
		mTxtLog.append(msg+"\n");
	}	

	public DatagramSocket getClientDatagram() {
		return mClientDatagram;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		//�ر�����
		mClientDatagram.close();
	}	
};
