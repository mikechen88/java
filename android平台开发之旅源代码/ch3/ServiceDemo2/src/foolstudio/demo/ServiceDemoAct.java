package foolstudio.demo;

import foolstudio.demo.service.EchoService;
import foolstudio.demo.service.IEchoService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceDemoAct extends Activity implements OnClickListener {
	
    private Button mBtnBind = null;
    private Button mBtnUnbind = null;
    private Button mBtnTransaction = null;
    private EditText mTxtName = null;
    //
	private IEchoService mService = null;
	
    private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = IEchoService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mService = null;
		}    	
    };	

    /** Called when the activity is first created. */    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnBind = (Button)findViewById(R.id.BTN_BIND);
        mBtnUnbind = (Button)findViewById(R.id.BTN_UNBIND);
        mBtnTransaction = (Button)findViewById(R.id.BTN_TRANSACTION);
        //
        mTxtName = (EditText)findViewById(R.id.TXT_NAME);
        
        mBtnBind.setOnClickListener(this);
        mBtnUnbind.setOnClickListener(this);
        mBtnTransaction.setOnClickListener(this);
        
        setButtons(false);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_BIND: {
				doBind();
				break;
			}
			case R.id.BTN_UNBIND: {
				doUnbind();
				break;
			}
			case R.id.BTN_TRANSACTION: {
				doTrasaction();
				break;
			}			
		}
	}
	
	private void doBind() {
		// TODO Auto-generated method stub
		bindService(new Intent(EchoService.class.getName() ), 
					mConnection, 
					Context.BIND_AUTO_CREATE);
		setButtons(true);
	}	

	private void doTrasaction() {
		// TODO Auto-generated method stub
		String echo = null;
		
		try {
			echo = mService.getEcho(mTxtName.getText().toString().trim() );
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Toast.makeText(this, echo, Toast.LENGTH_LONG).show();
	}

	private void doUnbind() {
		// TODO Auto-generated method stub
		unbindService(mConnection);
		setButtons(false);
	}
	
	private void setButtons(boolean isAvailable) {
		mBtnBind.setEnabled(!isAvailable);
		mTxtName.setEnabled(isAvailable);
		mBtnTransaction.setEnabled(isAvailable);
		mBtnUnbind.setEnabled(isAvailable);		
	}
};