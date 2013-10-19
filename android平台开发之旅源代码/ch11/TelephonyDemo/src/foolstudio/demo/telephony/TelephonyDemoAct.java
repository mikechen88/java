package foolstudio.demo.telephony;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.EditText;

public class TelephonyDemoAct extends Activity {
	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        init();
    }

    //初始化
	private void init() {
		// TODO Auto-generated method stub
		TelephonyManager man = 
			(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		printText("Country: " + man.getSimCountryIso() );
		printText("Operator: " + man.getSimOperator() );
		printText("OperatorName: " + man.getSimOperatorName() );
		printText("SN: " + man.getSimSerialNumber() );
		printText("State: " + getSimStateDesc(man.getSimState() ) );
		//
		printText("DeviceId: " + man.getDeviceId() );
		printText("SoftwareVersion: " + man.getDeviceSoftwareVersion() );
		printText("Line1Number: " + man.getLine1Number() );
		printText("PhoneType: " + getPhoneTypeDesc(man.getPhoneType() ) );
		//
		printText("CountryIso: " + man.getNetworkCountryIso() );
		printText("NetworkOperator: " + man.getNetworkOperator() );
		printText("OperatorName: " + man.getNetworkOperatorName() );
		printText("NetworkType: " + getNetworkTypeDesc(man.getNetworkType() ) );
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
	
	//获取SIM卡状态描述
	public static String getSimStateDesc(int type) {
		switch(type) {
			case TelephonyManager.SIM_STATE_ABSENT: {
				return ("Absend");
			}
			case TelephonyManager.SIM_STATE_NETWORK_LOCKED: {
				return ("Locked");
			}
			case TelephonyManager.SIM_STATE_PIN_REQUIRED: {
				return ("Pin Required");
			}
			case TelephonyManager.SIM_STATE_PUK_REQUIRED: {
				return ("PUK Required");
			}
			case TelephonyManager.SIM_STATE_READY: {
				return ("Ready");
			}
			case TelephonyManager.SIM_STATE_UNKNOWN: {
				return ("Unknown");
			}			
		}
		
		return ("Unknown");
	}		
	
	//获取电话类型描述	
	public static String getPhoneTypeDesc(int type) {
		switch(type) {
			case TelephonyManager.PHONE_TYPE_GSM: {
				return ("GMS");
			}
			case TelephonyManager.PHONE_TYPE_NONE: {
				return ("NONE");
			}		
		}
		
		return ("NONE");
	}	
	
	//获取网络类型描述	
	public static String getNetworkTypeDesc(int type) {
		switch(type) {
			case TelephonyManager.NETWORK_TYPE_EDGE: {
				return ("EDGE");
			}
			case TelephonyManager.NETWORK_TYPE_GPRS: {
				return ("GPRS");
			}
			case TelephonyManager.NETWORK_TYPE_UMTS: {
				return ("UMTS");
			}
			case TelephonyManager.NETWORK_TYPE_UNKNOWN: {
				return ("Unknown");
			}			
		}
		
		return ("Unknown");
	}
};