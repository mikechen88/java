package com.szy.handler.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ExampleService extends Service
{
	private static final int COOLSZY=1;
	private static final int KUKA=2;
	
	private static final String TAG="ExampleService";
	
	private ServiceHandler handler;
	
	private class ServiceHandler extends Handler
	{
		
		public ServiceHandler(Looper looper)
		{
			super(looper);
		}

		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case COOLSZY:
				Log.i(TAG, "handlerMessage-->"+msg.obj);
				break;
			case KUKA:
				Log.i(TAG, "handlerMessage-->"+msg.obj);
				break;
			default:
				break;
			}
			stopSelf(msg.arg1);
		}
	}
	
	@Override
	public void onCreate()
	{
		Log.i(TAG, "ExampleService-->onCreate()");
		Looper looper=Looper.getMainLooper();
		handler=new ServiceHandler(looper);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i(TAG, "ExampleService-->onStartCommand()");
		Message msg=handler.obtainMessage();
		msg.what=KUKA;
		msg.arg1=startId;
		msg.obj="kuka";
		//handler.sendMessage(msg)-->handler.sendMessageDelayed(msg, 0);-->handler.sendMessageAtTime()-->queue.enqueueMessage(
		handler.sendMessage(msg);
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		Log.i(TAG, "ExampleService-->onDestory()");
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
