package com.szy.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class MyReceiver extends BroadcastReceiver
{

	private static final String TAG = "MyReceiver";

	
	public MyReceiver()
	{
		Log.i(TAG, "MyReceiver");
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i(TAG, "onReceive");
	}

}
