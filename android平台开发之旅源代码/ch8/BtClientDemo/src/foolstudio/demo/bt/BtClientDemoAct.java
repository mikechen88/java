package foolstudio.demo.bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BtClientDemoAct extends Activity implements OnClickListener {	
	private EditText mTxtMsg = null;
	private Button mBtnStart = null;
	private EditText mTxtContents = null;	
	
	private BluetoothAdapter mAdapter = null;
	private BtDiscoverReceiver mReceiver = null;	
	private IntentFilter mIntentFilter = null;
	private ArrayList<BluetoothDevice> mDevices = new ArrayList<BluetoothDevice>();

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
		
		mReceiver = new BtDiscoverReceiver(mDevices);
		mIntentFilter = new IntentFilter(BtDiscoverReceiver.class.getName() );
        
		mTxtMsg = (EditText)findViewById(R.id.TXT_MSG);   
        mBtnStart = (Button)findViewById(R.id.BTN_SEND);
        mTxtContents = (EditText)findViewById(R.id.TXT_LOG);        
        
        mBtnStart.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SEND: {
				clearText();
				try {
					doStart();
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
		enableDiscover();
		
		this.registerReceiver(mReceiver, mIntentFilter);
		
		mAdapter.startDiscovery();
		
		while(mAdapter.isDiscovering() ) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.mDevices.size() < 1) {
			return;
		}
		
		mAdapter.cancelDiscovery();
		
		BluetoothDevice device = this.mDevices.get(0);
		BluetoothSocket socket = 
			device.createRfcommSocketToServiceRecord(BtConfig.BT_UUID);
		
		talkVia(socket);
		
		socket.close();
	}

	//允许启动蓝牙功能
	private void enableBt() {
		if(!mAdapter.isEnabled() ) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, BtConfig.REQUEST_ENABLE_BT);
		}
	}

	//允许探索
	private void enableDiscover() {
		if(mAdapter.getScanMode() == 
			BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			enableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 180);
			startActivityForResult(enableIntent, BtConfig.REQUEST_ENABLE_DISCOVER);
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
		else if(requestCode == BtConfig.REQUEST_ENABLE_DISCOVER) {
			if(resultCode != Activity.RESULT_OK) {
				Toast.makeText(this, "查找设备功能没有启动成功！", Toast.LENGTH_LONG).show();
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}	
	
	private void talkVia(BluetoothSocket socket) throws IOException {
		// TODO Auto-generated method stub
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		
		PrintWriter pw = new PrintWriter(os);
		pw.println(mTxtMsg.getText().toString().trim() );
		pw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		String line = null;
		
		while((line=br.readLine()) != null) {
			printText(line);
		}
		
		br.close();
		pw.close();
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};