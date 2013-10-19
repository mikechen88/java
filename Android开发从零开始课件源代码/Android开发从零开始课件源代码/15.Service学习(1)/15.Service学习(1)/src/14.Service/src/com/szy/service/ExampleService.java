package com.szy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ExampleService extends Service
{
	private static final String TAG="ExampleService";
	
	@Override
	public void onCreate()
	{
		Log.i(TAG, "ExampleService-->onCreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy()
	{
		Log.i(TAG, "ExampleService-->onDestroy");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i(TAG, "ExampleService-->onStartCommand");
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

}
