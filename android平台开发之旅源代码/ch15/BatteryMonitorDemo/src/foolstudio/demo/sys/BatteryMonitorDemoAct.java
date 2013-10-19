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

	//注销状态侦听者
	private void doUnregister() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(mReceiver);
		
		mBtnUnregister.setEnabled(false);
		mBtnRegister.setEnabled(true);
	}

	private void doAction() {
		// TODO Auto-generated method stub
	}

	//注册状态侦听者
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
	
	//嵌套类
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
	
	//获取状态描述
	private String getStatusDesc(int status) {
		switch(status) {
			case BatteryManager.BATTERY_STATUS_CHARGING: {
				return ("充电ing");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_DISCHARGING: {
				return ("放电中");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_FULL: {
				return ("充满");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING: {
				return ("没充电");
				//break;
			}
			case BatteryManager.BATTERY_STATUS_UNKNOWN: {
				return ("未知状态");
				//break;
			}
		}
		
		return ("未知状态");
	}

	//获取健康状态描述
	public String getHealthDesc(int health) {
		// TODO Auto-generated method stub
		switch(health) {
			case BatteryManager.BATTERY_HEALTH_DEAD: {
				return ("需更换");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_GOOD: {
				return ("良好");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE: {
				return ("电压过高");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_OVERHEAT: {
				return ("过热");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_UNKNOWN: {
				return ("未知状态");
				//break;
			}
			case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE: {
				return ("未定义失败");
				//break;
			}			
		}
	
		return ("未知状态");
	}

	//获取电源类型描述
	public String getPluggedDesc(int plugged) {
		// TODO Auto-generated method stub
		switch(plugged) {
			case BatteryManager.BATTERY_PLUGGED_AC: {
				return ("交流电源");
				//break;
			}
			case BatteryManager.BATTERY_PLUGGED_USB: {
				return ("USB电源");
				//break;
			}
		}
	
		return ("未知状态");
	}
};