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
			Log.i(TAG, "MyService线程ID："+Thread.currentThread().getId());
			Log.i(TAG, "文件下载....");
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

}
