package foolstudio.demo.sys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BatteryMonitorDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnRegister = null;
	private Button mBtnAction = null;
	private Button mBtnUnregister = null;	
	private EditText mTxtContents = null;	
	//
	private BatteryReceiver mReceiver = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnRegister = (Button)findViewById(R.id.BTN_REGISTER);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUnregister = (Button)findViewById(R.id.BTN_UNREGISTER);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnRegister.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUnregister.setOnClickListener(this);
        
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		mReceiver = new BatteryReceiver();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_REGISTER: {
				doRegister();
				break;
			}	
			case R.id.BTN_ACTION: {
				doAction();
				break;
			}			
			case R.id.BTN_UNREGISTER: {
				doUnregister();
				break;
			}		
		}
	}

	//ע��״̬������
	private void doUnregister() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(mReceiver);
		
		mBtnUnregister.setEnabled(false);
		mBtnRegister.setEnabled(true);
	}

	private void doAction() {
		// TODO Auto-generated method stub
	}

	//ע��״̬������
	private void doRegister() {
		// TODO Auto-generated method stub
		this.registerReceiver(mReceiver, 
				  			  new IntentFilter(Intent.ACTION_BATTERY_CHANGED) );		
		mBtnUnregister.setEnabled(true);
		mBtnRegister.setEnabled(false);
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
	
	//Ƕ����
	class BatteryReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub	
			Bundle bundle = intent.getExtras();
			
			int level = bundle.getInt("level");
			int status = bundle.getInt("status");
			int iconSmall = bundle.getInt("icon-small");
			int temperature = bundle.getInt("temperature");
			int plugged = bundle.getInt("plugged");
			int scale = bundle.getInt("scale");
			boolean present = bundle.getBoolean("present");
			int health = bundle.getInt("health");
			String technology = bundle.getString("technology");
			int voltage = bundle.getInt("voltage");
			//
			printText("level="+level+"%");
			printText("status="+getStatusDesc(status) );
			printText("iconSmall="+iconSmall);
			printText("temperature="+temperature);
			printText("plugged="+getPluggedDesc(plugged) );
			printText("scale="+scale);
			printText("present="+present);
			printText("health="+getHealthDesc(health) );
			printText("technology="+technology);
			printText("voltage="+voltage);
		}
	};
	
	//��ȡ״̬����
	private String getStatusDesc(int status) {
		switch(status) {
			case BatteryManager.BATTERY_STATUS_CHARGING: {
				return ("���ing");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_DISCHARGING: {
				return ("�ŵ���");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_FULL: {
				return ("����");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING: {
				return ("û���");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_UNKNOWN: {
				return ("δ֪״̬");
				//break;
			}
		}
		
		return ("δ֪״̬");
	}

	//��ȡ����״̬����
	public String getHealthDesc(int health) {
		// TODO Auto-generated method stub
		switch(health) {
			case BatteryManager.BATTERY_HEALTH_DEAD: {
				return ("�����");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_GOOD: {
				return ("����");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE: {
				return ("��ѹ����");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_OVERHEAT: {
				return ("����");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_UNKNOWN: {
				return ("δ֪״̬");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE: {
				return ("δ����ʧ��");
				//break;
			}			
		}
	
		return ("δ֪״̬");
	}

	//��ȡ��Դ��������
	public String getPluggedDesc(int plugged) {
		// TODO Auto-generated method stub
		switch(plugged) {
			case BatteryManager.BATTERY_PLUGGED_AC: {
				return ("������Դ");
				//break;
			}
			case BatteryManager.BATTERY_PLUGGED_USB: {
				return ("USB��Դ");
				//break;
			}
		}
	
		return ("δ֪״̬");
	}
};