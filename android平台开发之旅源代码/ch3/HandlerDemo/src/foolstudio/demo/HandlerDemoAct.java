package foolstudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HandlerDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtMsg = null;
	private Handler mHandler = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnStart = (Button)findViewById(R.id.BTN_START);
        mTxtMsg = (EditText)findViewById(R.id.TXT_MSG);
        
        btnStart.setOnClickListener(this);
        
        //��ʼ���߳���Ϣ������
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				Bundle bundle = msg.getData();
				String sender = bundle.getCharSequence("Sender").toString();
				String data = bundle.getString("Msg");
				
				addMsg(sender+"|"+data);
			}
		};        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_START: {
				doStart();
				break;
			}
		}
	}

	//�����߳�
	private void doStart() {
		// TODO Auto-generated method stub
		LocalThread t = new LocalThread(this, mHandler);
		t.start();
	}
	
	//�÷������ܱ�������View֮����̵߳���
	public void addMsg(String msg) {
		mTxtMsg.append(msg+"\n");
	}
};