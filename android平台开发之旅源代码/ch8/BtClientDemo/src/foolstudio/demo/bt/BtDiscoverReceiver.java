package foolstudio.demo.bt;

import java.util.ArrayList;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BtDiscoverReceiver extends BroadcastReceiver {
	
	private ArrayList<BluetoothDevice> mDevices = null;
	
	public BtDiscoverReceiver(ArrayList<BluetoothDevice> devices) {
		super();
		// TODO Auto-generated constructor stub
		mDevices = devices;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		
		if(!BluetoothDevice.ACTION_FOUND.equals(action) ) {
			return;
		}
		
		BluetoothDevice device = 
			intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		
		Log.d(this.getClass().getSimpleName(), 
			  device.getAddress() + ", " + device.getName() );
		
		mDevices.add(device);
	}
};