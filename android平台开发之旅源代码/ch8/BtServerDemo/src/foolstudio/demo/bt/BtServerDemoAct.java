package foolstudio.demo.bt;

import java.io.IOException;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BtServerDemoAct extends Activity implements OnClickListener {	
	private Button mBtnStart = null;
	private Button mBtnStop = null;
	private EditText mTxtContents = null;	
	
	private BluetoothAdapter mAdapter = null;    
	private BtServerThread mServerThread = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mAdapter == null) {
			//Toast.makeText(this, "当前设备不支持蓝牙！", Toast.LENGTH_LONG).show();
			//this.finish();
			//return;
		}
		
		mServerThread = new BtServerThread(mAdapter);
        
        mBtnStart = (Button)findViewById(R.id.BTN_START);
        mBtnStop = (Button)findViewById(R.id.BTN_STOP);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_START: {
				clearText();
				try {
					doStart();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}	
			case R.id.BTN_STOP: {
				clearText();
				try {
					doStop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}		
		}
	}

	private void doStart() throws IOException {
		// TODO Auto-generated method stub
		enableBt();
		mServerThread.start();		
	}
	
	//允许启动蓝牙功能
	private void enableBt() {
		if(!mAdapter.isEnabled() ) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, BtConfig.REQUEST_ENABLE_BT);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == BtConfig.REQUEST_ENABLE_BT) {
			if(resultCode != Activity.RESULT_OK) {
				Toast.makeText(this, "蓝牙没有启动成功！", Toast.LENGTH_LONG).show();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}	

	private void doStop() throws IOException {
		// TODO Auto-generated method stub
		mServerThread.setCanRunning(false);
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};