package com.szy.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ExampleIntentService extends IntentService
{
	protected static final String TAG = "IntentActivity";
	
	public ExampleIntentService()
	{
		super("ExampleIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		
		try
		{
			Log.i(TAG, "MyService�߳�ID��"+Thread.currentThread().getId());
			Log.i(TAG, "�ļ�����....");
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

}
