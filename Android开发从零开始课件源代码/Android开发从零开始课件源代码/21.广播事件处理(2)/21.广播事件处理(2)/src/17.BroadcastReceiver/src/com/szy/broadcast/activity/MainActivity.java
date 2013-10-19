package com.szy.broadcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.szy.broadcast.receiver.SecondReceiver;

public class MainActivity extends Activity
{
	protected static final String ACTION = "com.szy.broadcast.ACTION";
	private Button btnBroadcast;
	private Button registerReceiver;
	private Button unregisterReceiver;
	private SecondReceiver receiver;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnBroadcast=(Button)findViewById(R.id.btnBroadcast);
		btnBroadcast.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent();
				intent.setAction(ACTION);
				sendBroadcast(intent);
			}
		});
		
		registerReceiver=(Button)findViewById(R.id.btnregisterReceiver);
		registerReceiver.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				receiver=new SecondReceiver();
				IntentFilter filter=new IntentFilter();
				filter.addAction(ACTION);
				registerReceiver(receiver, filter);
			}
		});
		
		unregisterReceiver=(Button)findViewById(R.id.btnunregisterReceiver);
		unregisterReceiver.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				unregisterReceiver(receiver);
			}
		});
	}
}